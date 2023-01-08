package com.lx.controller;

import com.lx.domain.ResponseResult;
import com.lx.domain.dto.LocationDto;
import com.lx.domain.dto.RoomCityDto;
import com.lx.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/room")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @GetMapping
    public ResponseResult getRoom(){
        return roomService.getRoom();
    }

    @PostMapping("/roomList")
    public ResponseResult roomList(RoomCityDto roomCityDto){
        return roomService.roomList(roomCityDto);
    }

    @GetMapping("/{id}")
    public ResponseResult roomDetail(@PathVariable("id") Long id){
        return roomService.roomDetail(id);
    }

    @PostMapping("/distance")
    public ResponseResult distanceLocation(@RequestBody LocationDto locationDto){
        return roomService.distanceLocation(locationDto);
    }
}
