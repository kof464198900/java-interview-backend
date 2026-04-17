package com.interview.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.interview.entity.UserError;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 错题Mapper接口
 */
@Mapper
public interface UserErrorMapper extends BaseMapper<UserError> {
    
    /**
     * 检查是否已存在于错题本
     */
    @Select("SELECT COUNT(*) FROM user_error WHERE user_id = #{userId} AND question_id = #{questionId}")
    int checkError(@Param("userId") Long userId, @Param("questionId") Long questionId);
}
