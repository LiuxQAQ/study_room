package com.lx.controller;

import com.lx.domain.ResponseResult;
import com.lx.domain.dto.ManagementStateDto;
import com.lx.service.ManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/management")
public class ManagementController {
 
    @Autowired
    private ManagementService managementService;

    @GetMapping
    public ResponseResult applyList(){
        return managementService.applyList();
    }

    @GetMapping("/{id}")
    public ResponseResult managementDetail(@PathVariable("id") Long id){
        return managementService.managementDetail(id);
    }

    @PutMapping
    public ResponseResult updateState(@RequestBody ManagementStateDto stateDto){
        return managementService.updateState(stateDto);
    }

    @GetMapping("/orderList")
    public ResponseResult orderList(Integer pageNum,Integer pageSize,Integer state){
        return managementService.orderList(pageNum,pageSize,state);
    }

}
