package com.technivaaran.coe.dtos;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResponseDto {

    private String userName;
    private String email;
    private String status;
    private LocalDateTime lastLoginOn;
    private boolean firstLogin;
}
