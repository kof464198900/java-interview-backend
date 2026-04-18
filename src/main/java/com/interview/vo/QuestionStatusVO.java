package com.interview.vo;

import lombok.Data;

@Data
public class QuestionStatusVO {
    private Long questionId;
    private Integer status; // 0=未答 1=正确 2=错误
}