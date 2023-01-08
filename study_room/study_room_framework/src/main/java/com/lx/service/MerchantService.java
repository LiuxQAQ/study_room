package com.lx.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lx.domain.ResponseResult;
import com.lx.domain.dto.*;
import com.lx.domain.entity.Merchant;


/**
 * (Merchant)表服务接口
 *
 * @author makejava
 * @since 2022-11-20 10:51:36
 */
public interface MerchantService extends IService<Merchant> {

    ResponseResult createRoom(CreateRoomDto createRoomDto);

    ResponseResult updateRoom(UpdateRoomDto updateRoomDto);

    ResponseResult applyRoom(ApplyRoomDto applyRoomDto);

    ResponseResult visual(VisualDto visualDto);

    ResponseResult getRoom(GetRoomDto getRoomDto);

}
