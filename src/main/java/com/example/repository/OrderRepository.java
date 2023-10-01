package com.example.repository;

import com.example.dto.CreateOrderResponseDto;
import com.example.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OrderRepository extends JpaRepository<Order, String> {

    @Query("SELECT new com.example.dto.CreateOrderResponseDto(ord.orderId, cus.customerName, prod.productDescription, " +
            "prod.productPrice, ord.quantity, ord.amount, ord.orderDate) " +
            "FROM Order AS ord " +
            "JOIN ord.customer AS cus " +
            "JOIN ord.product AS prod " +
            "WHERE ord.orderId = :orderId")
    CreateOrderResponseDto getLatestOrderById(@Param("orderId") String orderId);

    @Query("SELECT new com.example.dto.CreateOrderResponseDto(ord.orderId, cus.customerName, prod.productDescription, " +
            "prod.productPrice, ord.quantity, ord.amount, ord.orderDate) " +
            "FROM Order AS ord " +
            "JOIN ord.customer AS cus " +
            "JOIN ord.product AS prod")
    Page<CreateOrderResponseDto> findAllOrders(Pageable pageable);
}
