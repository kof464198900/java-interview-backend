package com.interview.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.interview.entity.UserCollect;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 收藏Mapper接口
 */
@Mapper
public interface UserCollectMapper extends BaseMapper<UserCollect> {
    
    /**
     * 检查是否已收藏
     */
    @Select("SELECT COUNT(*) FROM user_collect WHERE user_id = #{userId} AND question_id = #{questionId}")
    int checkCollect(@Param("userId") Long userId, @Param("questionId") Long questionId);
}
