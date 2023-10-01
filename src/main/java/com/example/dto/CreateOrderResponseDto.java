package com.example.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
public class CreateOrderResponseDto {

    @Getter
    @Setter
    private String orderId;

    @Getter
    @Setter
    private String customerName;

    @Getter
    @Setter
    private String productDescription;

    @Getter
    @Setter
    private String productPrice;

    @Getter
    @Setter
    private int quantity;

    @Getter
    @Setter
    private BigDecimal amount;

    @Getter
    @Setter
    private LocalDateTime orderDate;
}
