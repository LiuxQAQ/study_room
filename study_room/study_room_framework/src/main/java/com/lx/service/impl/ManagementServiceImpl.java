package com.lx.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lx.domain.ResponseResult;
import com.lx.domain.dto.ManagementStateDto;
import com.lx.domain.entity.Management;
import com.lx.domain.entity.Order;
import com.lx.domain.entity.Room;
import com.lx.domain.vo.ManagementDetailVo;
import com.lx.domain.vo.ManagementListVo;
import com.lx.domain.vo.OrderListVo;
import com.lx.domain.vo.PageVo;
import com.lx.mapper.ManagementMapper;
import com.lx.service.*;
import com.lx.utils.BeanCopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * (Management)表服务实现类
 *
 * @author makejava
 * @since 2022-11-20 16:49:46
 */
@Service("managementService")
public class ManagementServiceImpl extends ServiceImpl<ManagementMapper, Management> implements ManagementService {

    @Autowired
    private MerchantService merchantService;
    @Autowired
    private RoomService roomService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private UserService userService;


    @Override
    public ResponseResult applyList() {
        LambdaQueryWrapper<Management> queryWrapper = new LambdaQueryWrapper<>();
//        queryWrapper.like(StringUtils.hasText(state.toString()),Management::getState,state);
        queryWrapper.orderByAsc(Management::getState);

//        Page<Management> page = new Page<>(pageNum, pageSize);
//        page(page,queryWrapper);
//        List<Management> managements = page.getRecords();
        List<Management> managements = list(queryWrapper);

        managements.stream()
                .map(management -> management.setMerchantName(merchantService.getById(management.getMid()).getName()))
                .map(management -> management.setRoomName(roomService.getById(management.getRid()).getName()))
                .collect(Collectors.toList());
        List<ManagementListVo> managementListVos = BeanCopyUtils.copyBeanList(managements, ManagementListVo.class);

        return ResponseResult.okResult(managementListVos);
    }

    @Override
    public ResponseResult managementDetail(Long id) {
        Management management = getById(id);
        ManagementDetailVo managementDetailVo = new ManagementDetailVo(roomService.getById(management.getRid()), merchantService.getById(management.getMid()));
        return ResponseResult.okResult(managementDetailVo);
    }

    @Override
    public ResponseResult updateState(ManagementStateDto stateDto) {
        LambdaUpdateWrapper<Management> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(Management::getId,stateDto.getId())
                .set(Management::getState,stateDto.getState());
        update(updateWrapper);
        int state = 2;
        if (stateDto.getState()==1) state=0;
        if (stateDto.getState()==2) state=1;

        LambdaUpdateWrapper<Room> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(Room::getId,stateDto.getRid())
                .set(Room::getState,state);
        roomService.update(wrapper);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult orderList(Integer pageNum,Integer pageSize,Integer state) {
        LambdaQueryWrapper<Order> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Objects.nonNull(state)&&state>=0,Order::getState,state);

        // 分页查询
        Page<Order> page = new Page<>(pageNum, pageSize);
        orderService.page(page,wrapper);
        List<Order> orders = page.getRecords();
        List<OrderListVo> orderListVos = BeanCopyUtils.copyBeanList(orders, OrderListVo.class);

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm");

        orderListVos.stream()
                .map(orderListVo -> orderListVo.setNickName(userService.getById(orderListVo.getUid()).getNickName()))
                .map(orderListVo -> orderListVo.setMinutes((int) (orderListVo.getEndTime().getTime()
                        - orderListVo.getStartTime().getTime())
                        /(1000 * 60)))
                .collect(Collectors.toList());

        PageVo pageVo = new PageVo(orderListVos, page.getTotal());
        return ResponseResult.okResult(pageVo);
    }
}
