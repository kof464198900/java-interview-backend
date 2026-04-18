package com.interview.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.interview.entity.Resource;
import com.interview.mapper.ResourceMapper;
import com.interview.common.result.Result;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 推荐资料控制器
 */
@RestController
@RequestMapping("/api/resource")
@RequiredArgsConstructor
public class ResourceController {
    
    private final ResourceMapper resourceMapper;
    
    /**
     * 获取推荐资料列表
     */
    @GetMapping("/list")
    @ApiOperation("获取推荐资料列表")
    public Result<List<Resource>> getResourceList() {
        LambdaQueryWrapper<Resource> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Resource::getStatus, 1)
                .orderByAsc(Resource::getSort);
        return Result.success(resourceMapper.selectList(wrapper));
    }
}