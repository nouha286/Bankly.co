package com.example.gatewayservice.Dto;

import lombok.Data;

@Data
public class UserDto {
    private long id;
    private String email;
    private String token;
}
