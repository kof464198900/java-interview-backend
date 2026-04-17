package com.interview.controller;

import com.interview.dto.AnswerDTO;
import com.interview.service.UserService;
import com.interview.vo.PageResult;
import com.interview.vo.QuestionVO;
import com.interview.vo.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 用户控制器
 */
@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@Api(tags = "用户管理")
public class UserController {
    
    private final UserService userService;
    
    /**
     * 获取用户信息
     */
    @GetMapping("/{id}")
    @ApiOperation("获取用户信息")
    public Result<com.interview.entity.User> getUserInfo(
            @ApiParam("用户ID") @PathVariable Long id) {
        return Result.success(userService.getUserById(id));
    }
    
    /**
     * 获取用户统计信息
     */
    @GetMapping("/{id}/stats")
    @ApiOperation("获取用户统计")
    public Result<Map<String, Object>> getUserStats(
            @ApiParam("用户ID") @PathVariable Long id) {
        return Result.success(userService.getUserStats(id));
    }
    
    /**
     * 获取我的收藏列表
     */
    @GetMapping("/{id}/collect")
    @ApiOperation("获取收藏列表")
    public Result<PageResult<QuestionVO>> getCollectList(
            @ApiParam("用户ID") @PathVariable Long id,
            @ApiParam("页码") @RequestParam(defaultValue = "1") Long page,
            @ApiParam("每页条数") @RequestParam(defaultValue = "10") Long size) {
        return Result.success(userService.getCollectList(id, page, size));
    }
    
    /**
     * 收藏题目
     */
    @PostMapping("/collect")
    @ApiOperation("收藏题目")
    public Result<Void> collect(
            @ApiParam("用户ID") @RequestParam Long userId,
            @ApiParam("题目ID") @RequestParam Long questionId) {
        userService.collect(userId, questionId);
        return Result.success();
    }
    
    /**
     * 取消收藏
     */
    @DeleteMapping("/collect")
    @ApiOperation("取消收藏")
    public Result<Void> uncollect(
            @ApiParam("用户ID") @RequestParam Long userId,
            @ApiParam("题目ID") @RequestParam Long questionId) {
        userService.uncollect(userId, questionId);
        return Result.success();
    }
    
    /**
     * 获取错题列表
     */
    @GetMapping("/{id}/error")
    @ApiOperation("获取错题列表")
    public Result<PageResult<QuestionVO>> getErrorList(
            @ApiParam("用户ID") @PathVariable Long id,
            @ApiParam("页码") @RequestParam(defaultValue = "1") Long page,
            @ApiParam("每页条数") @RequestParam(defaultValue = "10") Long size) {
        return Result.success(userService.getErrorList(id, page, size));
    }
    
    /**
     * 添加错题
     */
    @PostMapping("/error")
    @ApiOperation("添加错题")
    public Result<Void> addError(
            @ApiParam("用户ID") @RequestParam Long userId,
            @ApiParam("题目ID") @RequestParam Long questionId,
            @ApiParam("用户答案") @RequestParam(required = false) String userAnswer) {
        userService.addError(userId, questionId, userAnswer);
        return Result.success();
    }
    
    /**
     * 获取做题记录
     */
    @GetMapping("/{id}/record")
    @ApiOperation("获取做题记录")
    public Result<PageResult<QuestionVO>> getRecordList(
            @ApiParam("用户ID") @PathVariable Long id,
            @ApiParam("页码") @RequestParam(defaultValue = "1") Long page,
            @ApiParam("每页条数") @RequestParam(defaultValue = "10") Long size) {
        return Result.success(userService.getRecordList(id, page, size));
    }
    
    /**
     * 提交答题记录
     */
    @PostMapping("/answer")
    @ApiOperation("提交答题")
    public Result<Void> submitAnswer(
            @ApiParam("用户ID") @RequestParam Long userId,
            @RequestBody AnswerDTO answerDTO) {
        userService.submitAnswer(userId, answerDTO);
        return Result.success();
    }
}