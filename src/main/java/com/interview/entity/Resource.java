package com.interview.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.time.LocalDateTime;

/**
 * 推荐资料实体类
 */
@Data
@TableName("resource")
public class Resource {
    
    /**
     * 资料ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    
    /**
     * 资料标题
     */
    private String title;
    
    /**
     * 描述
     */
    private String description;
    
    /**
     * 图片URL
     */
    private String imageUrl;
    
    /**
     * 链接URL
     */
    private String linkUrl;
    
    /**
     * 排序
     */
    private Integer sort;
    
    /**
     * 状态 0-禁用 1-正常
     */
    private Integer status;
    
    /**
     * 创建时间
     */
    private LocalDateTime createdAt;
}
