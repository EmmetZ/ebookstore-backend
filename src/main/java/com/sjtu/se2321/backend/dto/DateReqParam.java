package com.sjtu.se2321.backend.dto;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class DateReqParam {
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDate start;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDate end;
}
