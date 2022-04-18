package com.example.crud.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class AccountReadResponseDto {

    private String userId;
    private String password;
    private String email;
    private String phone;

    @Builder
    public AccountReadResponseDto(String userId, String password, String email, String phone) {
        this.userId = userId;
        this.password = password;
        this.email = email;
        this.phone = phone;
    }
}