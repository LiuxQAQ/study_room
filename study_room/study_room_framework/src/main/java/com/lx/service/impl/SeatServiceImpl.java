package com.lx.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lx.domain.ResponseResult;
import com.lx.domain.dto.SeatListDto;
import com.lx.domain.entity.Room;
import com.lx.domain.entity.Seat;
import com.lx.domain.vo.SeatChosenVo;
import com.lx.domain.vo.SeatListVo;
import com.lx.mapper.SeatMapper;
import com.lx.service.RoomService;
import com.lx.service.SeatService;
import com.lx.utils.BeanCopyUtils;
import com.lx.utils.CompareTwoTimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * (Seat)表服务实现类
 *
 * @author makejava
 * @since 2022-11-20 09:53:25
 */
@Service("seatService")
public class SeatServiceImpl extends ServiceImpl<SeatMapper, Seat> implements SeatService {

    @Autowired
    private RoomService roomService;

    @Override
    public ResponseResult getSeatList(SeatListDto seatListDto) {
        LambdaQueryWrapper<Seat> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Seat::getRid,seatListDto.getRid())
                .eq(Seat::getDatetime,seatListDto.getDateTime());
        List<Seat> list = list(queryWrapper);
        Room room = roomService.getById(seatListDto.getRid());
        SeatListVo seatListVos = new SeatListVo();
        if (Optional.ofNullable(list).isPresent()){
            // 将时间冲突的选出来
            List<Seat> seats = list.stream()
                    .filter(new Predicate<Seat>() {
                        @Override
                        public boolean test(Seat seat) {
                            return CompareTwoTimeUtil.compareTwoTime(seat.getStartTime(), seat.getEndTime(),
                                    seatListDto.getStartTime(), seatListDto.getEndTime());
                        }
                    }).collect(Collectors.toList());

//            seatListVos = BeanCopyUtils.copyBeanList(list, SeatListVo.class);
            List<Integer> collect = seats.stream()
                    .map(Seat::getSeat)
                    .collect(Collectors.toList());

            List<SeatChosenVo> change = change(collect, room.getSeat());

            seatListVos.setSeats(change);
        }
        return ResponseResult.okResult(seatListVos);
    }

    private List<SeatChosenVo> change(List<Integer> list,int num){
        List<SeatChosenVo> integers = new ArrayList<>();
        for (int i = 1; i <= num; i++) {
            if (list.contains(i)){
                integers.add(new SeatChosenVo().setSeatNum(i).setChosen(false));
            }else{
                integers.add(new SeatChosenVo().setSeatNum(i).setChosen(true));
            }
        }
        return integers;
    }
}
