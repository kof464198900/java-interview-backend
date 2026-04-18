package com.interview.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.interview.entity.UserAnswer;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserAnswerMapper extends BaseMapper<UserAnswer> {
}