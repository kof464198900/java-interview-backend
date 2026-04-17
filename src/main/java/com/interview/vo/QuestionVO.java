package com.interview.vo;

import lombok.Data;

/**
 * 题目VO
 */
@Data
public class QuestionVO {
    
    /**
     * 题目ID
     */
    private Long id;
    
    /**
     * 分类ID
     */
    private Long categoryId;
    
    /**
     * 分类名称
     */
    private String categoryName;
    
    /**
     * 题干
     */
    private String title;
    
    /**
     * 面试回答重点
     */
    private String answerKey;
    
    /**
     * 扩展知识
     */
    private String expandKnowledge;
    
    /**
     * 难度 1-简单 2-中等 3-困难
     */
    private Integer difficulty;
    
    /**
     * 查看次数
     */
    private Integer viewCount;
    
    /**
     * 是否收藏 0-未收藏 1-已收藏
     */
    private Integer isCollect;
}
