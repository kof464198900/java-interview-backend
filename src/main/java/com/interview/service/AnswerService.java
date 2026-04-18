package com.interview.service;

import com.interview.vo.AnswerCardVO;
import com.interview.vo.AnswerResultVO;
import com.interview.vo.HomeStatsVO;

public interface AnswerService {
    HomeStatsVO getHomeStats(Long userId);
    AnswerResultVO submitAnswer(Long userId, Long questionId, String userAnswer, Integer answerMode);
    AnswerCardVO getAnswerCard(Long userId, Long categoryId);
}