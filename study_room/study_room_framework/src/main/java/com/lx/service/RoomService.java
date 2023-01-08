package com.lx.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lx.domain.ResponseResult;
import com.lx.domain.dto.LocationDto;
import com.lx.domain.dto.RoomCityDto;
import com.lx.domain.entity.Room;


/**
 * (Room)表服务接口
 *
 * @author makejava
 * @since 2022-11-01 11:22:58
 */
public interface RoomService extends IService<Room> {

    ResponseResult roomList(RoomCityDto roomCityDto);

    ResponseResult roomDetail(Long id);

    ResponseResult getRoom();

    ResponseResult distanceLocation(LocationDto locationDto);
}
