package com.interview.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.time.LocalDateTime;

/**
 * 做题记录实体类
 */
@Data
@TableName("user_record")
public class UserRecord {
    
    /**
     * 记录ID
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
     * 是否正确 0-错误 1-正确
     */
    private Integer isCorrect;
    
    /**
     * 用户答案
     */
    private String userAnswer;
    
    /**
     * 答题时间
     */
    private LocalDateTime answeredAt;
}
