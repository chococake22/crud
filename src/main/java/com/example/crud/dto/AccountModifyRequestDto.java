package com.example.crud.dto;

import com.example.crud.entity.Account;
import lombok.Builder;
import lombok.Data;

@Data   // Getter, Setter, toString, equals, RequiredArgConstructor
public class AccountModifyRequestDto {

    private String password;
    private String email;
    private String phone;

    @Builder
    public AccountModifyRequestDto(String password, String email, String phone) {
        this.password = password;
        this.email = email;
        this.phone = phone;
    }

    // Dto -> Entity(DAO)
    // 데이터에 저장할 경우 사용
    public Account toEntity() {
        return Account.builder()
                .password(password)
                .email(email)
                .phone(phone)
                .build();
    }
}
