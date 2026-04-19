package com.interview.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.interview.common.exception.BusinessException;
import com.interview.dto.AnswerDTO;
import com.interview.entity.User;
import com.interview.entity.UserCollect;
import com.interview.entity.UserError;
import com.interview.entity.UserRecord;
import com.interview.mapper.*;
import com.interview.service.UserService;
import com.interview.vo.PageResult;
import com.interview.vo.QuestionVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 用户服务实现类
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    
    private final UserMapper userMapper;
    private final UserCollectMapper collectMapper;
    private final UserErrorMapper errorMapper;
    private final UserRecordMapper recordMapper;
    private final QuestionMapper questionMapper;
    
    @Override
    public User getUserById(Long id) {
        return userMapper.selectById(id);
    }
    
    @Override
    public PageResult<QuestionVO> getCollectList(Long userId, Long page, Long size) {
        Page<UserCollect> pageParam = new Page<>(page, size);
        LambdaQueryWrapper<UserCollect> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserCollect::getUserId, userId);
        
        Page<UserCollect> collectPage = collectMapper.selectPage(pageParam, wrapper);
        
        List<Long> questionIds = collectPage.getRecords().stream()
                .map(UserCollect::getQuestionId)
                .collect(Collectors.toList());
        
        if (questionIds.isEmpty()) {
            return new PageResult<>(page, size, 0L, Collections.EMPTY_LIST);
        }
        
        List<QuestionVO> questions = questionMapper.selectBatchIds(questionIds).stream()
                .map(q -> {
                    QuestionVO vo = new QuestionVO();
                    vo.setId(q.getId());
                    vo.setCategoryId(q.getCategoryId());
                    vo.setTitle(q.getTitle());
                    vo.setAnswerKey(q.getAnswerKey());
                    vo.setExpandKnowledge(q.getExpandKnowledge());
                    vo.setDifficulty(q.getDifficulty());
                    vo.setViewCount(q.getViewCount());
                    vo.setIsCollect(1);
                    return vo;
                })
                .collect(Collectors.toList());
        
        return new PageResult<>(page, size, collectPage.getTotal(), questions);
    }
    
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void collect(Long userId, Long questionId) {
        int count = collectMapper.checkCollect(userId, questionId);
        if (count > 0) {
            throw new BusinessException(400, "已收藏");
        }
        
        UserCollect collect = new UserCollect();
        collect.setUserId(userId);
        collect.setQuestionId(questionId);
        collectMapper.insert(collect);
    }
    
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void uncollect(Long userId, Long questionId) {
        LambdaQueryWrapper<UserCollect> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserCollect::getUserId, userId)
                .eq(UserCollect::getQuestionId, questionId);
        collectMapper.delete(wrapper);
    }
    
    @Override
    public PageResult<QuestionVO> getErrorList(Long userId, Long page, Long size) {
        Page<UserError> pageParam = new Page<>(page, size);
        LambdaQueryWrapper<UserError> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserError::getUserId, userId);
        
        Page<UserError> errorPage = errorMapper.selectPage(pageParam, wrapper);
        
        List<Long> questionIds = errorPage.getRecords().stream()
                .map(UserError::getQuestionId)
                .collect(Collectors.toList());
        
        if (questionIds.isEmpty()) {
            return new PageResult<>(page, size, 0L, new java.util.ArrayList<>());
        }
        
        List<QuestionVO> questions = questionMapper.selectBatchIds(questionIds).stream()
                .map(q -> {
                    QuestionVO vo = new QuestionVO();
                    vo.setId(q.getId());
                    vo.setCategoryId(q.getCategoryId());
                    vo.setTitle(q.getTitle());
                    vo.setAnswerKey(q.getAnswerKey());
                    vo.setExpandKnowledge(q.getExpandKnowledge());
                    vo.setDifficulty(q.getDifficulty());
                    vo.setViewCount(q.getViewCount());
                    return vo;
                })
                .collect(Collectors.toList());
        
        return new PageResult<>(page, size, errorPage.getTotal(), questions);
    }
    public void addError(Long userId, Long questionId, String userAnswer) {
        int count = errorMapper.checkError(userId, questionId);
        if (count > 0) {
            return;
        }
        
        UserError error = new UserError();
        error.setUserId(userId);
        error.setQuestionId(questionId);
        error.setWrongAnswer(userAnswer);
        error.setWrongCount(1);
        errorMapper.insert(error);
    }
    
    @Override
    public PageResult<QuestionVO> getRecordList(Long userId, Long page, Long size) {
        Page<UserRecord> pageParam = new Page<>(page, size);
        LambdaQueryWrapper<UserRecord> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserRecord::getUserId, userId)
                .orderByDesc(UserRecord::getAnsweredAt);
        
        Page<UserRecord> recordPage = recordMapper.selectPage(pageParam, wrapper);
        
        List<Long> questionIds = recordPage.getRecords().stream()
                .map(UserRecord::getQuestionId)
                .collect(Collectors.toList());
        
        if (questionIds.isEmpty()) {
            return new PageResult<>(page, size, 0L, new java.util.ArrayList<>());
        }
        
        List<QuestionVO> questions = questionMapper.selectBatchIds(questionIds).stream()
                .map(q -> {
                    QuestionVO vo = new QuestionVO();
                    vo.setId(q.getId());
                    vo.setCategoryId(q.getCategoryId());
                    vo.setTitle(q.getTitle());
                    vo.setAnswerKey(q.getAnswerKey());
                    vo.setExpandKnowledge(q.getExpandKnowledge());
                    vo.setDifficulty(q.getDifficulty());
                    vo.setViewCount(q.getViewCount());
                    return vo;
                })
                .collect(Collectors.toList());
        
        return new PageResult<>(page, size, recordPage.getTotal(), questions);
    }
    
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void submitAnswer(Long userId, AnswerDTO answerDTO) {
        UserRecord record = new UserRecord();
        record.setUserId(userId);
        record.setQuestionId(answerDTO.getQuestionId());
        record.setIsCorrect(answerDTO.getIsCorrect());
        record.setUserAnswer(answerDTO.getUserAnswer());
        recordMapper.insert(record);
        
        if (answerDTO.getIsCorrect() == 0) {
            addError(userId, answerDTO.getQuestionId(), answerDTO.getUserAnswer());
        }
    }
    
    @Override
    public Map<String, Object> getUserStats(Long userId) {
        Map<String, Object> stats = new HashMap<>();
        
        // 答题总数
        Long totalCount = recordMapper.selectCount(new LambdaQueryWrapper<UserRecord>()
                .eq(UserRecord::getUserId, userId));
        stats.put("totalCount", totalCount);
        
        // 正确数
        Long correctCount = recordMapper.selectCount(new LambdaQueryWrapper<UserRecord>()
                .eq(UserRecord::getUserId, userId)
                .eq(UserRecord::getIsCorrect, 1));
        stats.put("correctCount", correctCount);
        
        // 正确率
        double correctRate = totalCount > 0 ? (correctCount * 100.0 / totalCount) : 0;
        stats.put("correctRate", String.format("%.1f", correctRate));
        
        // 收藏数
        Long collectCount = collectMapper.selectCount(new LambdaQueryWrapper<UserCollect>()
                .eq(UserCollect::getUserId, userId));
        stats.put("collectCount", collectCount);
        
        // 错题数
        Long errorCount = errorMapper.selectCount(new LambdaQueryWrapper<UserError>()
                .eq(UserError::getUserId, userId));
        stats.put("errorCount", errorCount);
        
        return stats;
    }
}
