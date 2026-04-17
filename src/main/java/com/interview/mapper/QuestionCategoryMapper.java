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
    @Select("SELECT c.id, c.name, c.icon, c.sort, COUNT(q.id) as questionCount " +
            "FROM question_category c " +
            "LEFT JOIN question q ON c.id = q.category_id AND q.status = 1 " +
            "GROUP BY c.id " +
            "ORDER BY c.sort")
    java.util.List<com.interview.vo.CategoryVO> selectCategoryWithCount();
}
