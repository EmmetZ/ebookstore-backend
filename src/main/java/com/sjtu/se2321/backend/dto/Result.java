package com.sjtu.se2321.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Result<T> {
    private String message;
    private T data;
    private boolean ok;

    public static Result<Void> error(String message) {
        return new Result<>(message, null, false);
    }

    public static <T> Result<T> error(String message, T data) {
        return new Result<>(message, data, false);
    }

    public static Result<Void> success(String message) {
        return new Result<>(message, null, true);
    }

    public static <T> Result<T> success(String message, T data) {
        return new Result<>(message, data, true);
    }
}