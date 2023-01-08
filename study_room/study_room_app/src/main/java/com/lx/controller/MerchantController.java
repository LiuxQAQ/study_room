package com.lx.controller;

import com.lx.domain.ResponseResult;
import com.lx.domain.dto.*;
import com.lx.service.MerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/merchant")
public class MerchantController {

    @Autowired
    private MerchantService merchantService;

    @PostMapping("/createRoom")
    public ResponseResult createRoom(@RequestBody CreateRoomDto createRoomDto){
        return merchantService.createRoom(createRoomDto);
    }

    @PutMapping
    public ResponseResult updateRoom(@RequestBody UpdateRoomDto updateRoom){
        return merchantService.updateRoom(updateRoom);
    }

    @PostMapping
    public ResponseResult getRoom(@RequestBody GetRoomDto getRoomDto){
        return merchantService.getRoom(getRoomDto);
    }

    @PostMapping("/applyRoom")
    public ResponseResult applyRoom(@RequestBody ApplyRoomDto applyRoomDto){
        return merchantService.applyRoom(applyRoomDto);
    }

    @PostMapping("/visual")
    public ResponseResult visual(@RequestBody VisualDto visualDto){
        return merchantService.visual(visualDto);
    }


}
