package com.interview.vo;

import lombok.Data;

/**
 * 分页结果类
 */
@Data
public class PageResult<T> {
    
    /**
     * 当前页码
     */
    private Long current;
    
    /**
     * 每页条数
     */
    private Long size;
    
    /**
     * 总记录数
     */
    private Long total;
    
    /**
     * 数据列表
     */
    private java.util.List<T> records;
    
    public PageResult() {
    }
    
    public PageResult(Long current, Long size, Long total, java.util.List<T> records) {
        this.current = current;
        this.size = size;
        this.total = total;
        this.records = records;
    }
}
