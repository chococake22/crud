package com.example.crud.entity;

import com.example.crud.dto.AccountModifyRequestDto;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Builder
@Entity(name = "Account")
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accSeq;
    private String userId;
    private String password;
    private String email;
    private String phone;


    // 수정
    public void update(AccountModifyRequestDto request) {
        this.password = request.getPassword();
        this.email = request.getEmail();
        this.phone = request.getPhone();
    }
}
