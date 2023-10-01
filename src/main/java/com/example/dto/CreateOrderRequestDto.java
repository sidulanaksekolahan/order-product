package com.example.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class CreateOrderRequestDto {

    @NotNull(message = "can not be null")
    @NotEmpty(message = "can not be empty")
    private String customerId;

    @NotNull(message = "can not be null")
    @NotEmpty(message = "can not be empty")
    private String productId;

    @NotNull(message = "can not be null")
    private Integer quantity;
}
