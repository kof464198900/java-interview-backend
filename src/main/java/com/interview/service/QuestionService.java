package com.interview.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.interview.vo.CategoryVO;
import com.interview.vo.QuestionVO;
import com.interview.vo.PageResult;

/**
 * 题库服务接口
 */
public interface QuestionService {
    
    /**
     * 获取分类列表
     */
    java.util.List<CategoryVO> getCategoryList();
    
    /**
     * 分页获取题目列表
     */
    PageResult<QuestionVO> getQuestionList(Long categoryId, Long page, Long size, Boolean hasOptions);
    
    /**
     * 获取题目详情
     */
    QuestionVO getQuestionDetail(Long id, Long userId);
    
    /**
     * 增加题目浏览次数
     */
    void increaseViewCount(Long id);
}
