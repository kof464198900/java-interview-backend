package com.interview.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.interview.entity.UserRecord;
import org.apache.ibatis.annotations.Mapper;

/**
 * 做题记录Mapper接口
 */
@Mapper
public interface UserRecordMapper extends BaseMapper<UserRecord> {
}
