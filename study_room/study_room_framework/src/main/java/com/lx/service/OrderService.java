package com.lx.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lx.domain.ResponseResult;
import com.lx.domain.dto.AddOrderDto;
import com.lx.domain.dto.OrderTypeDto;
import com.lx.domain.entity.Order;

import java.text.ParseException;


/**
 * (Order)表服务接口
 *
 * @author makejava
 * @since 2022-11-20 09:37:06
 */
public interface OrderService extends IService<Order> {

    ResponseResult addOrder(AddOrderDto orderDto) throws ParseException;

    ResponseResult queryOrder(int state);
}
