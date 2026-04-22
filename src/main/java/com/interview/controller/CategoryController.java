package com.interview.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.interview.entity.Question;
import com.interview.entity.QuestionCategory;
import com.interview.mapper.QuestionCategoryMapper;
import com.interview.mapper.QuestionMapper;
import com.interview.common.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
    
    @Autowired
    private QuestionCategoryMapper categoryMapper;
    
    @Autowired
    private QuestionMapper questionMapper;
    
    @GetMapping("/list")
    public Result<List<Map>> getCategoryTree() {
        QueryWrapper<QuestionCategory> wrapper = new QueryWrapper<>();
        wrapper.orderByAsc("sort");
        List<QuestionCategory> all = categoryMapper.selectList(wrapper);
        
        List<QuestionCategory> roots = all.stream()
            .filter(c -> c.getParentId() == null || c.getParentId().equals(0L) || c.getParentId().equals(0))
            .collect(Collectors.toList());
        
        List<Map> result = new ArrayList<>();
        
        List<Long> rootIds = roots.stream().map(QuestionCategory::getId).collect(Collectors.toList());
        Map<Long, Long> countMap = new HashMap<>();
        if (!rootIds.isEmpty()) {
            QueryWrapper<Question> qwrapper = new QueryWrapper<>();
            qwrapper.select("category_id", "count(*) as total");
            qwrapper.in("category_id", rootIds);
            qwrapper.eq("status", 1);
            qwrapper.ne("options", "");
            qwrapper.isNotNull("options");
            qwrapper.groupBy("category_id");
            List<Map<String, Object>> maps = questionMapper.selectMaps(qwrapper);
            for (Map<String, Object> m : maps) {
                Long catId = (Long) m.get("category_id");
                Long total = (long) m.get("total");
                countMap.put(catId,total);
            }
            for (Long id : rootIds) {
                countMap.putIfAbsent(id, 0L);
            }
        }
        
        for (QuestionCategory root : roots) {
            Map<String, Object> node = new HashMap<>();
            node.put("id", root.getId());
            node.put("name", root.getName());
            node.put("level", root.getLevel());
            node.put("questionCount", countMap.getOrDefault(root.getId(), 0L));
            
            List<QuestionCategory> children = all.stream()
                .filter(c -> root.getId().equals(c.getParentId()))
                .collect(Collectors.toList());
            if (!children.isEmpty()) {
                node.put("children", children);
            }
            result.add(node);
        }
        return Result.success(result);
    }
}