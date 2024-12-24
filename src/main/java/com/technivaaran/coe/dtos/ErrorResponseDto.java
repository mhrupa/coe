package com.technivaaran.coe.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorResponseDto {
    private String status;
    private String exception;
    private String message;
}
