package com.interview.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * 题目VO
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class QuestionVO {
    
    private Long id;
    
    private Long categoryId;
    
    private String categoryName;
    
    private String title;
    
    private String answerKey;
    
    private String expandKnowledge;
    
    private Integer difficulty;
    
    private Integer viewCount;
    
    private Integer isCollect;
    
    private Integer type;
    
    private String options;
    
    private String correctAnswer;
}
