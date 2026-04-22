package com.interview.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.interview.entity.Question;
import com.interview.vo.QuestionVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface QuestionMapper extends BaseMapper<Question> {
    
    IPage<QuestionVO> selectQuestionPage(Page<?> page, @Param("categoryId") Long categoryId, @Param("hasOptions") Boolean hasOptions);
    
    QuestionVO selectQuestionDetail(Long id);
}
