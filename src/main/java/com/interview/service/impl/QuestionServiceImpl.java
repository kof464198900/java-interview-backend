package com.interview.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.interview.common.exception.BusinessException;
import com.interview.entity.Question;
import com.interview.mapper.QuestionCategoryMapper;
import com.interview.mapper.QuestionMapper;
import com.interview.mapper.UserCollectMapper;
import com.interview.service.QuestionService;
import com.interview.vo.CategoryVO;
import com.interview.vo.PageResult;
import com.interview.vo.QuestionVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 题库服务实现类
 */
@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {
    
    private final QuestionCategoryMapper categoryMapper;
    private final QuestionMapper questionMapper;
    private final UserCollectMapper collectMapper;
    
    @Override
    public java.util.List<CategoryVO> getCategoryList() {
        return categoryMapper.selectCategoryWithCount();
    }
    
    @Override
    public PageResult<QuestionVO> getQuestionList(Long categoryId, Long page, Long size) {
        Page<QuestionVO> pageParam = new Page<>(page, size);
        IPage<QuestionVO> questionPage = questionMapper.selectQuestionPage(pageParam, categoryId);
        
        return new PageResult<>(
                questionPage.getCurrent(),
                questionPage.getSize(),
                questionPage.getTotal(),
                questionPage.getRecords()
        );
    }
    
    @Override
    public QuestionVO getQuestionDetail(Long id, Long userId) {
        QuestionVO questionVO = questionMapper.selectQuestionDetail(id);
        if (questionVO == null) {
            throw new BusinessException(404, "题目不存在");
        }
        
        // 检查是否已收藏
        if (userId != null) {
            int collectCount = collectMapper.checkCollect(userId, id);
            questionVO.setIsCollect(collectCount > 0 ? 1 : 0);
        } else {
            questionVO.setIsCollect(0);
        }
        
        return questionVO;
    }
    
    @Override
    public void increaseViewCount(Long id) {
        Question question = new Question();
        question.setId(id);
        question.setViewCount(1);
        questionMapper.updateById(question);
    }
}
