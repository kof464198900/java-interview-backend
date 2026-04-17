package com.interview.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.interview.entity.Resource;
import org.apache.ibatis.annotations.Mapper;

/**
 * 推荐资料Mapper接口
 */
@Mapper
public interface ResourceMapper extends BaseMapper<Resource> {
}
