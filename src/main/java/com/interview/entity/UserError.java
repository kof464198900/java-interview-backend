package com.interview.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.time.LocalDateTime;

/**
 * 错题实体类
 */
@Data
@TableName("user_error")
public class UserError {
    
    /**
     * 错题ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    
    /**
     * 用户ID
     */
    private Long userId;
    
    /**
     * 题目ID
     */
private Long questionId;
    
    private Long categoryId;
    
    private String wrongAnswer;
    
    private Integer wrongCount;
    
    private String userAnswer;
    
    /**
     * 添加时间
     */
    private LocalDateTime createdAt;
}
