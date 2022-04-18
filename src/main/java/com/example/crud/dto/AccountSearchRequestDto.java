package com.example.crud.dto;

import com.example.crud.entity.Account;
import lombok.Builder;
import lombok.Data;

@Data
public class AccountSearchRequestDto {

    private String userId;

    @Builder
    public AccountSearchRequestDto(String userId) {
        this.userId = userId;
    }

    public Account toEntity() {
        return Account.builder()
                .userId(userId)
                .build();
    }
}