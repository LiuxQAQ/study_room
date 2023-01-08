package com.lx.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lx.domain.ResponseResult;
import com.lx.domain.dto.SeatListDto;
import com.lx.domain.entity.Seat;


/**
 * (Seat)表服务接口
 *
 * @author makejava
 * @since 2022-11-20 09:53:25
 */
public interface SeatService extends IService<Seat> {

    ResponseResult getSeatList(SeatListDto seatListDto);
}
