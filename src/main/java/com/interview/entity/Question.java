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
    
    @TableId(type = IdType.AUTO)
    private Long id;
    
    private Long categoryId;
    
    private String title;
    
    private String answerKey;
    
    private String expandKnowledge;
    
    private Integer difficulty;
    
    private Integer viewCount;
    
    private Integer status;
    
    private Integer type;
    
    private String options;
    
    private String correctAnswer;
    
    private LocalDateTime createdAt;
    
    private LocalDateTime updatedAt;
}
