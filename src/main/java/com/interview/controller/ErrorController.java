package com.interview.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.interview.entity.Question;
import com.interview.entity.UserError;
import com.interview.mapper.QuestionMapper;
import com.interview.mapper.UserErrorMapper;
import com.interview.common.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/error")
public class ErrorController {
    
    @Autowired
    private UserErrorMapper userErrorMapper;
    @Autowired
    private QuestionMapper questionMapper;

    @GetMapping("/list")
    public Result<Map<String, Object>> list(@RequestParam(required = false) Long categoryId) {
        QueryWrapper<UserError> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", 1L);
        if (categoryId != null) {
            wrapper.eq("category_id", categoryId);
        }
        wrapper.orderByDesc("id");
        List<UserError> errors = userErrorMapper.selectList(wrapper);

        if (errors.isEmpty()) {
            Map<String, Object> result = new HashMap<>();
            result.put("list", Collections.emptyList());
            result.put("total", 0);
            return Result.success(result);
        }

        List<Long> questionIds = errors.stream()
                .map(UserError::getQuestionId)
                .distinct()
                .collect(Collectors.toList());

        Map<Long, Question> questionMap = questionMapper.selectBatchIds(questionIds).stream()
                .collect(Collectors.toMap(Question::getId, q -> q));

        List<Map<String, Object>> list = errors.stream()
                .map(error -> {
                    Question q = questionMap.get(error.getQuestionId());
                    Map<String, Object> item = new HashMap<>();
                    item.put("id", error.getId());
                    item.put("questionId", error.getQuestionId());
                    item.put("title", q != null ? q.getTitle() : "");
                    item.put("wrongAnswer", error.getWrongAnswer());
                    item.put("correctAnswer", q != null ? q.getCorrectAnswer() : "");
                    item.put("wrongCount", error.getWrongCount());
                    return item;
                })
                .collect(Collectors.toList());

        Map<String, Object> result = new HashMap<>();
        result.put("list", list);
        result.put("total", list.size());
        return Result.success(result);
    }


    @GetMapping("/stats")
    public Result<Map> stats() {
        QueryWrapper<UserError> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", 1L);
        long total = userErrorMapper.selectCount(wrapper);
        
        Map result = new HashMap();
        result.put("total", (int) total);
        return Result.success(result);
    }
    
    @DeleteMapping("/{questionId}")
    public Result<Void> remove(@PathVariable Long questionId) {
        QueryWrapper<UserError> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", 1L).eq("question_id", questionId);
        userErrorMapper.delete(wrapper);
        return Result.success(null);
    }
}