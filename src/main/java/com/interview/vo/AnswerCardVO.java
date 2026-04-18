package com.interview.vo;

import lombok.Data;
import java.util.List;

@Data
public class AnswerCardVO {
    private Integer total;
    private Integer answered;
    private Integer correct;
    private Integer wrong;
    private List<QuestionStatusVO> questions;
}