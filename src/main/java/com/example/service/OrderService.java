package com.example.service;

import com.example.dto.CreateOrderRequestDto;
import com.example.dto.CreateOrderResponseDto;
import org.springframework.data.domain.Page;

public interface OrderService {
    CreateOrderResponseDto createOrder(CreateOrderRequestDto dto);

    Page<CreateOrderResponseDto> findAllOrders(int page, int size);

//    List<OrderDto> findAll();
}
