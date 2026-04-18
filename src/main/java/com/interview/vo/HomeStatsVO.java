package com.interview.vo;

import lombok.Data;

@Data
public class HomeStatsVO {
    private Integer collectCount;
    private Integer answeredCount;
    private Integer errorCount;
    private Integer totalQuestion;
    private Integer progress;
    private Long lastCategoryId;
    private String lastCategoryName;
}