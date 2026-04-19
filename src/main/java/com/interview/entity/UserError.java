package com.interview.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

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
    
    /**
     * 分类ID
     */
    private Long categoryId;
    
    /**
     * 错误答案
     */
    private String wrongAnswer;
    
    /**
     * 错误次数
     */
    private Integer wrongCount;
}
