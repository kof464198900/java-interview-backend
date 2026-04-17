package com.interview.dto;

import lombok.Data;

/**
 * 答题请求DTO
 */
@Data
public class AnswerDTO {
    
    /**
     * 题目ID
     */
    private Long questionId;
    
    /**
     * 用户答案
     */
    private String userAnswer;
    
    /**
     * 是否正确 0-错误 1-正确
     */
    private Integer isCorrect;
}
