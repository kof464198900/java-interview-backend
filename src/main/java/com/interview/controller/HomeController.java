package com.interview.controller;

import com.interview.service.AnswerService;
import com.interview.vo.HomeStatsVO;
import com.interview.common.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/home")
public class HomeController {
    
    @Autowired
    private AnswerService answerService;
    
    @GetMapping("/stats")
    public Result<HomeStatsVO> getStats() {
        return Result.success(answerService.getHomeStats(1L));
    }
}