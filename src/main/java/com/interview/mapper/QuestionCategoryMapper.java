package com.interview.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.interview.entity.QuestionCategory;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * 题目分类Mapper接口
 */
@Mapper
public interface QuestionCategoryMapper extends BaseMapper<QuestionCategory> {
    
    /**
     * 查询分类及其题目数量
     */
    java.util.List<com.interview.vo.CategoryVO> selectCategoryWithCount();
}
