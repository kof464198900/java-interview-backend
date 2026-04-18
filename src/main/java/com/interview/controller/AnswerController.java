package com.interview.controller;

import com.interview.service.AnswerService;
import com.interview.vo.AnswerResultVO;
import com.interview.vo.AnswerCardVO;
import com.interview.common.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/answer")
public class AnswerController {
    
    @Autowired
    private AnswerService answerService;
    
    @PostMapping("/submit")
    public Result<AnswerResultVO> submit(
            @RequestParam Long questionId,
            @RequestParam String userAnswer,
            @RequestParam(defaultValue = "1") Integer answerMode) {
        return Result.success(answerService.submitAnswer(1L, questionId, userAnswer, answerMode));
    }
    
    @GetMapping("/card/{categoryId}")
    public Result<AnswerCardVO> getCard(@PathVariable Long categoryId) {
        return Result.success(answerService.getAnswerCard(1L, categoryId));
    }
}