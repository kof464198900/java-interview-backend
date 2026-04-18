package com.interview.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.interview.entity.Question;
import com.interview.entity.UserAnswer;
import com.interview.entity.UserError;
import com.interview.mapper.QuestionMapper;
import com.interview.mapper.QuestionCategoryMapper;
import com.interview.mapper.UserAnswerMapper;
import com.interview.mapper.UserCollectMapper;
import com.interview.mapper.UserErrorMapper;
import com.interview.service.AnswerService;
import com.interview.vo.AnswerCardVO;
import com.interview.vo.AnswerResultVO;
import com.interview.vo.HomeStatsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AnswerServiceImpl implements AnswerService {

    @Autowired
    private UserAnswerMapper userAnswerMapper;
    @Autowired
    private UserErrorMapper userErrorMapper;
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private QuestionCategoryMapper categoryMapper;
    @Autowired
    private UserCollectMapper userCollectMapper;

    @Override
    public HomeStatsVO getHomeStats(Long userId) {
        HomeStatsVO vo = new HomeStatsVO();
        
        QueryWrapper<UserAnswer> answerWrapper = new QueryWrapper<>();
        answerWrapper.eq("user_id", userId);
        vo.setAnsweredCount(userAnswerMapper.selectCount(answerWrapper));
        
        QueryWrapper<UserError> errorWrapper = new QueryWrapper<>();
        errorWrapper.eq("user_id", userId);
        vo.setErrorCount(userErrorMapper.selectCount(errorWrapper));
        
        QueryWrapper<Question> questionWrapper = new QueryWrapper<>();
        questionWrapper.eq("status", 1);
        vo.setTotalQuestion(questionMapper.selectCount(questionWrapper));
        
        vo.setCollectCount(userCollectMapper.selectCount(new QueryWrapper<UserAnswer>().eq("user_id", userId)));
        
        if (vo.getTotalQuestion() > 0) {
            vo.setProgress(vo.getAnsweredCount() * 100 / vo.getTotalQuestion());
        } else {
            vo.setProgress(0);
        }
        
        return vo;
    }

    @Override
    public AnswerResultVO submitAnswer(Long userId, Long questionId, String userAnswer, Integer answerMode) {
        Question question = questionMapper.selectById(questionId);
        AnswerResultVO result = new AnswerResultVO();
        
        boolean isCorrect = userAnswer != null && 
            userAnswer.equalsIgnoreCase(question.getCorrectAnswer());
        
        result.setIsCorrect(isCorrect);
        result.setCorrectAnswer(question.getCorrectAnswer());
        result.setExplanation(question.getAnswerKey());
        result.setUserAnswer(userAnswer);
        
        UserAnswer record = new UserAnswer();
        record.setUserId(userId);
        record.setQuestionId(questionId);
        record.setCategoryId(question.getCategoryId());
        record.setUserAnswer(userAnswer);
        record.setIsCorrect(isCorrect ? 1 : 0);
        record.setAnswerMode(answerMode != null ? answerMode : 1);
        userAnswerMapper.insert(record);
        
        if (!isCorrect) {
            QueryWrapper<UserError> wrapper = new QueryWrapper<>();
            wrapper.eq("user_id", userId).eq("question_id", questionId);
            UserError error = userErrorMapper.selectOne(wrapper);
            if (error != null) {
                error.setWrongCount(error.getWrongCount() + 1);
                error.setWrongAnswer(userAnswer);
                userErrorMapper.updateById(error);
            } else {
                error = new UserError();
                error.setUserId(userId);
                error.setQuestionId(questionId);
                error.setCategoryId(question.getCategoryId());
                error.setWrongAnswer(userAnswer);
                error.setWrongCount(1);
                userErrorMapper.insert(error);
            }
        } else {
            QueryWrapper<UserError> wrapper = new QueryWrapper<>();
            wrapper.eq("user_id", userId).eq("question_id", questionId);
            userErrorMapper.delete(wrapper);
        }
        
        return result;
    }

    @Override
    public AnswerCardVO getAnswerCard(Long userId, Long categoryId) {
        AnswerCardVO vo = new AnswerCardVO();
        QueryWrapper<UserAnswer> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", userId);
        if (categoryId != null) {
            wrapper.eq("category_id", categoryId);
        }
        wrapper.orderByDesc("created_at");
        List<UserAnswer> records = userAnswerMapper.selectList(wrapper);
        
        vo.setTotal(records.size());
        vo.setAnswered(records.size());
        long correct = records.stream().filter(r -> r.getIsCorrect() != null && r.getIsCorrect() == 1).count();
        vo.setCorrect((int) correct);
        vo.setWrong((int) (records.size() - correct));
        vo.setQuestions(new ArrayList<>());
        
        return vo;
    }
}