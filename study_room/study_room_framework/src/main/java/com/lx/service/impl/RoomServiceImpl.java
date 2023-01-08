package com.lx.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lx.constants.SystemConstants;
import com.lx.domain.ResponseResult;
import com.lx.domain.dto.LocationDto;
import com.lx.domain.dto.RoomCityDto;
import com.lx.domain.entity.Room;
import com.lx.domain.entity.Rp;
import com.lx.domain.vo.RoomDetailVo;
import com.lx.domain.vo.RoomDistanceVo;
import com.lx.domain.vo.RoomVo;
import com.lx.mapper.RoomMapper;
import com.lx.service.RoomService;
import com.lx.service.RpService;
import com.lx.utils.BeanCopyUtils;
import com.lx.utils.RedisCache;
import com.lx.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * (Room)表服务实现类
 *
 * @author makejava
 * @since 2022-11-01 11:22:58
 */
@Service("roomService")
public class RoomServiceImpl extends ServiceImpl<RoomMapper, Room> implements RoomService {

    @Autowired
    private RedisCache redisCache;

    @Override
    public ResponseResult roomList(RoomCityDto roomCityDto) {
        LambdaQueryWrapper<Room> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Objects.nonNull(roomCityDto.getDistrict()),Room::getDistrict,roomCityDto.getDistrict())
                .eq(Objects.nonNull(roomCityDto.getCity()),Room::getCity,roomCityDto.getCity())
                .eq(Room::getState, SystemConstants.ROOM_STATUS_NORMAL);

        List<Room> rooms = list(queryWrapper);
        List<RoomVo> roomVos = BeanCopyUtils.copyBeanList(rooms, RoomVo.class);
        return ResponseResult.okResult(roomVos);
    }

    @Override
    public ResponseResult roomDetail(Long id) {
        Room room = getById(id);
        RoomDetailVo roomDetailVo = BeanCopyUtils.copyBean(room, RoomDetailVo.class);
        return ResponseResult.okResult(roomDetailVo);
    }

    @Override
    public ResponseResult getRoom() {
        Long userId = SecurityUtils.getUserId();
        LambdaQueryWrapper<Room> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Room::getMId,userId);
        List<Room> list = list(queryWrapper);
        RoomVo roomVo = BeanCopyUtils.copyBean(list, RoomVo.class);
        return ResponseResult.okResult(roomVo);
    }

    @Override
    public ResponseResult distanceLocation(LocationDto locationDto) {

        List<Room> list = list(new LambdaQueryWrapper<Room>().eq(Room::getState, SystemConstants.ROOM_STATE_NORMAL));
        List<RoomDistanceVo> roomDistanceVos = BeanCopyUtils.copyBeanList(list, RoomDistanceVo.class);
        roomDistanceVos.stream()
                .map(roomDistanceVo -> roomDistanceVo.setDistance(
                        redisCache.getDistance(locationDto.getLongitude(),locationDto.getLatitude(),roomDistanceVo.getLongitude(),roomDistanceVo.getLatitude())))
                .collect(Collectors.toList());
        Collections.sort(roomDistanceVos);

        return ResponseResult.okResult(roomDistanceVos);
    }
}
