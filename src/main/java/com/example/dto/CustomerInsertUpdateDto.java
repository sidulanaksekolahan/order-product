package com.example.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class CustomerInsertUpdateDto {

    @NotNull(message = "can not be null")
    @NotEmpty(message = "can not be empty")
    private String customerName;

    @NotNull(message = "can not be null")
    @NotEmpty(message = "can not be empty")
    private String address;

    @NotNull(message = "can not be null")
    private Integer phone;
}
