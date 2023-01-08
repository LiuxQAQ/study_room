package com.lx.controller;

import com.lx.domain.ResponseResult;
import com.lx.domain.dto.SeatListDto;
import com.lx.domain.vo.SeatListVo;
import com.lx.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/seat")
public class SeatController {

    @Autowired
    private SeatService seatService;

    @PostMapping
    public ResponseResult getSeatList(@RequestBody SeatListDto seatListDto){
        return seatService.getSeatList(seatListDto);
    }


}
