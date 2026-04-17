package com.interview.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.time.LocalDateTime;

/**
 * 面试题实体类
 */
@Data
@TableName("question")
public class Question {
    
    /**
     * 题目ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    
    /**
     * 分类ID
     */
    private Long categoryId;
    
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
     * 状态 0-禁用 1-正常
     */
    private Integer status;
    
    /**
     * 创建时间
     */
    private LocalDateTime createdAt;
    
    /**
     * 更新时间
     */
    private LocalDateTime updatedAt;
}
