package com.interview.service;

import com.interview.dto.AnswerDTO;
import com.interview.vo.PageResult;
import com.interview.vo.QuestionVO;
import java.util.List;

/**
 * 用户服务接口
 */
public interface UserService {
    
    /**
     * 获取用户信息
     */
    com.interview.entity.User getUserById(Long id);
    
    /**
     * 获取我的收藏列表
     */
    PageResult<QuestionVO> getCollectList(Long userId, Long page, Long size);
    
    /**
     * 收藏题目
     */
    void collect(Long userId, Long questionId);
    
    /**
     * 取消收藏
     */
    void uncollect(Long userId, Long questionId);
    
    /**
     * 获取我的错题列表
     */
    PageResult<QuestionVO> getErrorList(Long userId, Long page, Long size);
    
    /**
     * 添加错题
     */
    void addError(Long userId, Long questionId, String userAnswer);
    
    /**
     * 获取做题记录
     */
    PageResult<QuestionVO> getRecordList(Long userId, Long page, Long size);
    
    /**
     * 提交答题记录
     */
    void submitAnswer(Long userId, AnswerDTO answerDTO);
    
    /**
     * 获取用户统计信息
     */
    java.util.Map<String, Object> getUserStats(Long userId);
}
