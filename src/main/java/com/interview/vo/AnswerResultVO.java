package com.interview.vo;

import lombok.Data;

@Data
public class AnswerResultVO {
    private Boolean isCorrect;
    private String correctAnswer;
    private String explanation;
    private String userAnswer;
}