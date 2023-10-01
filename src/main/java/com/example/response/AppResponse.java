package com.example.response;

import lombok.Data;

@Data
public class AppResponse {

    private int status;

    private Object message;

    private long timestamp;
}
