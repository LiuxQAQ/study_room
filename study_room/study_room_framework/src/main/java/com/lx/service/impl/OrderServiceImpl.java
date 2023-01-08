package com.lx.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lx.constants.SystemConstants;
import com.lx.domain.ResponseResult;
import com.lx.domain.dto.AddOrderDto;
import com.lx.domain.dto.OrderTypeDto;
import com.lx.domain.entity.Order;
import com.lx.domain.entity.Seat;
import com.lx.domain.vo.OrderTypeListVo;
import com.lx.mapper.OrderMapper;
import com.lx.service.OrderService;
import com.lx.service.RoomService;
import com.lx.service.SeatService;
import com.lx.utils.BeanCopyUtils;
import com.lx.utils.QrCodeUtil;
import com.lx.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * (Order)表服务实现类
 *
 * @author makejava
 * @since 2022-11-20 09:37:06
 */
@Service("orderService")
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    @Autowired
    private SeatService seatService;

    @Autowired
    private RoomService roomService;

    @Override
    public ResponseResult addOrder(AddOrderDto orderDto) throws ParseException {
        Seat seat = BeanCopyUtils.copyBean(orderDto, Seat.class);
        seatService.save(seat);
        Order order = BeanCopyUtils.copyBean(orderDto, Order.class);
        order.setState(SystemConstants.ORDER_STATE_PAY);
        SimpleDateFormat format = new SimpleDateFormat("yy-MM-dd hh:mm:ss");
        order.setDatetime(format.parse(format.format(new Date())));
        order.setCreateTime(format.parse(format.format(new Date())));
        String uuid = UUID.randomUUID().toString().replaceAll("-","");
        order.setCode(QrCodeUtil.creatRrCode(uuid,200,200));
        save(order);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult queryOrder(int state) {
        Long id = SecurityUtils.getUserId();
        LambdaQueryWrapper<Order> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Order::getUid,id)
                .eq(state==SystemConstants.ORDER_STATE_PAY,Order::getState,state)
                .eq(state==SystemConstants.ORDER_STATE_NO,Order::getState,state);

        List<Order> list = list(queryWrapper);
        List<OrderTypeListVo> orderTypeListVos = BeanCopyUtils.copyBeanList(list, OrderTypeListVo.class);
        orderTypeListVos.stream()
                .map(orderTypeListVo -> orderTypeListVo.setName(roomService.getById(orderTypeListVo.getRid()).getName()))
                .map(orderTypeListVo -> orderTypeListVo.setCity(roomService.getById(orderTypeListVo.getRid()).getCity()))
                .map(orderTypeListVo -> orderTypeListVo.setShowCode(false))
                .collect(Collectors.toList());

        return ResponseResult.okResult(orderTypeListVos);
    }
}
