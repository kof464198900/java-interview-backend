package com.interview.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.interview.entity.QuestionCategory;
import com.interview.mapper.QuestionCategoryMapper;
import com.interview.common.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
    
    @Autowired
    private QuestionCategoryMapper categoryMapper;
    
    @GetMapping("/list")
    public Result<List<Map>> getCategoryTree() {
        QueryWrapper<QuestionCategory> wrapper = new QueryWrapper<>();
        wrapper.orderByAsc("sort");
        List<QuestionCategory> all = categoryMapper.selectList(wrapper);
        
        System.out.println("All categories: " + all);
        
        List<QuestionCategory> roots = all.stream()
            .filter(c -> c.getParentId() == null || c.getParentId().equals(0L) || c.getParentId().equals(0))
            .collect(Collectors.toList());
        
        System.out.println("Root categories: " + roots);
        
        List<Map> result = new ArrayList<>();
        for (QuestionCategory root : roots) {
            Map<String, Object> node = new HashMap<>();
            node.put("id", root.getId());
            node.put("name", root.getName());
            node.put("level", root.getLevel());
            
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