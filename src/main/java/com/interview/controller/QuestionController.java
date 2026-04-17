package com.interview.controller;

import com.interview.service.QuestionService;
import com.interview.vo.PageResult;
import com.interview.vo.QuestionVO;
import com.interview.vo.Result;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 题库控制器
 */
@RestController
@RequestMapping("/api/question")
@RequiredArgsConstructor
public class QuestionController {
    
    private final QuestionService questionService;
    
    /**
     * 获取分类列表
     */
    @GetMapping("/category")
    @ApiOperation("获取分类列表")
    public Result<List<com.interview.vo.CategoryVO>> getCategoryList() {
        return Result.success(questionService.getCategoryList());
    }
    
    /**
     * 分页获取题目列表
     */
    @GetMapping("/list")
    @ApiOperation("获取题目列表")
    public Result<PageResult<QuestionVO>> getQuestionList(
            @ApiParam("分类ID") @RequestParam(required = false) Long categoryId,
            @ApiParam("页码") @RequestParam(defaultValue = "1") Long page,
            @ApiParam("每页条数") @RequestParam(defaultValue = "10") Long size) {
        return Result.success(questionService.getQuestionList(categoryId, page, size));
    }
    
    /**
     * 获取题目详情
     */
    @GetMapping("/{id}")
    @ApiOperation("获取题目详情")
    public Result<QuestionVO> getQuestionDetail(
            @ApiParam("题目ID") @PathVariable Long id,
            @ApiParam("用户ID") @RequestParam(required = false) Long userId) {
        return Result.success(questionService.getQuestionDetail(id, userId));
    }
    
    /**
     * 增加浏览次数
     */
    @PostMapping("/{id}/view")
    @ApiOperation("增加浏览次数")
    public Result<Void> increaseViewCount(@ApiParam("题目ID") @PathVariable Long id) {
        questionService.increaseViewCount(id);
        return Result.success();
    }
}
