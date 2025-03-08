package com.rammy.project.uber.uberApp.advices;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ApiResponse<T>{
    private LocalDateTime timeStamp;
    private T data;
    private ApiError error;

    public ApiResponse() {
        this.timeStamp=LocalDateTime.now();
    }

    public ApiResponse(ApiError error) {
        this();
        this.error = error;
    }

    public ApiResponse(T data) {
        this();
        this.data = data;
    }
}
