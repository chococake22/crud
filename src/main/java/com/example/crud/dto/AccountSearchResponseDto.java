package com.example.crud.dto;

import com.example.crud.entity.Account;
import lombok.Builder;
import lombok.Data;

@Data
public class AccountSearchResponseDto {

    private String userId;
    private String password;
    private String email;
    private String phone;

    @Builder
    public AccountSearchResponseDto(String userId, String password, String email, String phone) {
        this.userId = userId;
        this.password = password;
        this.email = email;
        this.phone = phone;
    }

    public Account toEntity() {
        return Account.builder()
                .userId(userId)
                .password(password)
                .email(email)
                .phone(phone)
                .build();
    }
}