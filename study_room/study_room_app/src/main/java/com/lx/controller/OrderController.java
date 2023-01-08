package com.lx.controller;

import com.lx.domain.ResponseResult;
import com.lx.domain.dto.AddOrderDto;
import com.lx.domain.dto.OrderTypeDto;
import com.lx.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/addOrder")
    public ResponseResult addOrder(@RequestBody AddOrderDto orderDto) throws ParseException {
        return orderService.addOrder(orderDto);
    }

    @GetMapping("/{state}")
    public ResponseResult queryOrder(@PathVariable("state") int state){
        return orderService.queryOrder(state);
    }

}
