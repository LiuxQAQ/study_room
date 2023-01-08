package com.lx.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lx.constants.SystemConstants;
import com.lx.domain.ResponseResult;
import com.lx.domain.dto.*;
import com.lx.domain.entity.Management;
import com.lx.domain.entity.Merchant;
import com.lx.domain.entity.Order;
import com.lx.domain.entity.Room;
import com.lx.domain.vo.VisualVo;
import com.lx.mapper.MerchantMapper;
import com.lx.service.ManagementService;
import com.lx.service.MerchantService;
import com.lx.service.OrderService;
import com.lx.service.RoomService;
import com.lx.utils.BeanCopyUtils;
import com.lx.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * (Merchant)表服务实现类
 *
 * @author makejava
 * @since 2022-11-20 10:51:36
 */
@Service("merchantService")
public class MerchantServiceImpl extends ServiceImpl<MerchantMapper, Merchant> implements MerchantService {


    @Autowired
    private RoomService roomService;

    @Autowired
    private ManagementService managementService;

    @Autowired
    private OrderService orderService;

    @Override
    public ResponseResult createRoom(CreateRoomDto createRoomDto) {

        Room room = BeanCopyUtils.copyBean(createRoomDto, Room.class);
        room.setState(SystemConstants.ROOM_STATE_WAIT);
//        room.setState(SystemConstants.ROOM_STATE_APPLY);
        roomService.save(room);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult updateRoom(UpdateRoomDto updateRoomDto) {
        Room room = BeanCopyUtils.copyBean(updateRoomDto, Room.class);
        LambdaUpdateWrapper<Room> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(Room::getId,room.getId());
        roomService.update(room,updateWrapper);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult applyRoom(ApplyRoomDto applyRoomDto) {
        Management management = new Management();
        management.setMid(1L);
        management.setRid(applyRoomDto.getId());
        management.setState(SystemConstants.MANAGEMENT_STATE_WAIT);
        managementService.save(management);

        LambdaUpdateWrapper<Room> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(Room::getId,applyRoomDto.getId())
                .set(Room::getState,SystemConstants.ROOM_STATE_APPLY);
        roomService.update(updateWrapper);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult visual(VisualDto visualDto) {
        LambdaQueryWrapper<Order> queryWrapper = new LambdaQueryWrapper<>();

        queryWrapper.eq(Order::getRid,visualDto.getRid())
                .eq(Order::getState,SystemConstants.ORDER_STATE_PAY)
                .like(Order::getStartTime,visualDto.getDate())
                .orderByDesc(Order::getStartTime);

        List<Order> list = orderService.list(queryWrapper);
        List<VisualVo> visualVos = monthHeader(list);
        return ResponseResult.okResult(visualVos);
    }

    @Override
    public ResponseResult getRoom(GetRoomDto getRoomDto) {
        LambdaQueryWrapper<Room> roomLambdaQueryWrapper = new LambdaQueryWrapper<>();
        roomLambdaQueryWrapper.eq(Room::getMId,getRoomDto.getMId());
        List<Room> list = roomService.list(roomLambdaQueryWrapper);
        return ResponseResult.okResult(list);
    }

    public List<VisualVo> monthHeader(List<Order> list){
        ArrayList<VisualVo> visualVos = new ArrayList<>();
        DateFormat df = new SimpleDateFormat("yyyy-MM");
        String[] months = {"-01","-02","-03","-04","-05","-06","-07","-08","-09","-10","-11","-12"};
        for (String month : months) {
            int totalPrices = 0;
            int nums = 0;
            String date = null;
            for (Order order : list) {
//                System.out.println(df.format(order.getStartTime()));
                if (df.format(order.getStartTime()).contains(month)) {

                    totalPrices = totalPrices+order.getTotalPrice();
                    nums++;
                    date = df.format(order.getStartTime());
                }

            }
            visualVos.add(new VisualVo(date, nums, totalPrices));
        }
        return visualVos;
    }

}
