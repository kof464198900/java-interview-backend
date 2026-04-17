package com.interview.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.interview.entity.Question;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 题目Mapper接口
 */
@Mapper
public interface QuestionMapper extends BaseMapper<Question> {
    
    /**
     * 分页查询题目（包含分类名称）
     */
    @Select("SELECT q.*, c.name as categoryName " +
            "FROM question q " +
            "LEFT JOIN question_category c ON q.category_id = c.id " +
            "WHERE q.status = 1 " +
            "<if test='categoryId != null'>AND q.category_id = #{categoryId}</if> " +
            "ORDER BY q.created_at DESC")
    IPage<com.interview.vo.QuestionVO> selectQuestionPage(Page<?> page, @Param("categoryId") Long categoryId);
    
    /**
     * 查询题目详情（包含分类名称）
     */
    @Select("SELECT q.*, c.name as categoryName " +
            "FROM question q " +
            "LEFT JOIN question_category c ON q.category_id = c.id " +
            "WHERE q.id = #{id}")
    com.interview.vo.QuestionVO selectQuestionDetail(Long id);
}
