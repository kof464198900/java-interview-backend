package com.interview.vo;

import lombok.Data;
import java.time.LocalDateTime;

/**
 * 分类VO
 */
@Data
public class CategoryVO {
    
    /**
     * 分类ID
     */
    private Long id;
    
    /**
     * 分类名称
     */
    private String name;
    
    /**
     * 图标
     */
    private String icon;
    
    /**
     * 排序
     */
    private Integer sort;
    
    /**
     * 题目数量
     */
    private Long questionCount;
}
