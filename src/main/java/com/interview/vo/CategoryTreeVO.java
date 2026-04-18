package com.interview.vo;

import lombok.Data;
import java.util.List;

@Data
public class CategoryTreeVO {
    private Long id;
    private String name;
    private Integer level;
    private List<CategoryTreeVO> children;
}