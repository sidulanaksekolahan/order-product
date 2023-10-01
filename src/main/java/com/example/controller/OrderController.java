package com.example.controller;

import com.example.dto.CreateOrderRequestDto;
import com.example.dto.CreateOrderResponseDto;
import com.example.response.AppResponse;
import com.example.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    private OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<AppResponse> createOrder(@Valid @RequestBody CreateOrderRequestDto dto) {

        CreateOrderResponseDto createOrderResponseDto = orderService.createOrder(dto);

        AppResponse appResponse = new AppResponse();
        appResponse.setStatus(HttpStatus.CREATED.value());
        appResponse.setMessage(createOrderResponseDto);
        appResponse.setTimestamp(System.currentTimeMillis());

        return new ResponseEntity<>(appResponse, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Page<CreateOrderResponseDto>> findAllOrders(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size
    ) {
        Page<CreateOrderResponseDto> allOrders = orderService.findAllOrders(page, size);

        return new ResponseEntity<>(allOrders, HttpStatus.OK);
    }

}
