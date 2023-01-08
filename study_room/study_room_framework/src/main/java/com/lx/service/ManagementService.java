package com.lx.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lx.domain.ResponseResult;
import com.lx.domain.dto.ManagementStateDto;
import com.lx.domain.entity.Management;


/**
 * (Management)表服务接口
 *
 * @author makejava
 * @since 2022-11-20 16:49:46
 */
public interface ManagementService extends IService<Management> {

    ResponseResult applyList();

    ResponseResult managementDetail(Long id);

    ResponseResult updateState(ManagementStateDto stateDto);

    ResponseResult orderList(Integer pageNum,Integer pageSize,Integer state);

}
