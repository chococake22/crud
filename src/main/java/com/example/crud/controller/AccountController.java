package com.example.crud.controller;

import com.example.crud.dto.*;
import com.example.crud.entity.Account;
import com.example.crud.repository.AccountRepository;
import com.example.crud.service.AccountService;
import io.swagger.annotations.ApiOperation;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/account")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    //dd

    // 회원 개별 조회
    // Optional
    @ApiOperation(value = "회원 개별 조회", notes = "개별 회원 정보를 조회합니다.")
    @GetMapping("/{accSeq}")
    public ResponseEntity<AccountReadResponseDto> findAccountInfoOne(@PathVariable Long accSeq) {
        return new ResponseEntity(accountService.getAccountInfoOne(accSeq), HttpStatus.OK);
    }

    // 회원 전체 조회
    @ApiOperation(value = "회원 전체 조회", notes = "전체 회원 정보를 조회합니다.")
    @GetMapping("/all")
    public ResponseEntity<List<AccountReadResponseDto>> findAccountInfoAll() {
        return new ResponseEntity(accountService.getAccountInfoAll(), HttpStatus.OK);
    }

    // 회원 검색
    @ApiOperation(value = "회원 검색", notes = "회원을 검색합니다.")
    @GetMapping("/search")
    public ResponseEntity<List<AccountSearchResponseDto>> searchAccount(@RequestParam String keyword) {
        return new ResponseEntity(accountService.searchAccount(keyword), HttpStatus.OK);
    }

    // 회원 가입
    @ApiOperation(value = "회원 생성", notes = "회원을 생성합니다.")
    @PostMapping
    public ResponseEntity<AccountCreateRequestDto> createAccount(@RequestBody AccountCreateRequestDto request) {
        return new ResponseEntity(accountService.addAccount(request), HttpStatus.OK);
    }

    // 회원 정보 수정
    @ApiOperation(value = "회원 정보 수정", notes = "회원 정보를 수정합니다.")
    @PutMapping("/{accSeq}")
    public ResponseEntity<AccountModifyRequestDto> updateAccountInfo(@PathVariable Long accSeq, @RequestBody AccountModifyRequestDto request) {
        return new ResponseEntity(accountService.modifyAccountInfo(accSeq, request), HttpStatus.OK);
    }

    // 회원 삭제
    @ApiOperation(value = "회원 삭제", notes = "회원을 삭제합니다.")
    @DeleteMapping("/{accSeq}")
    public ResponseEntity deleteAccount(@PathVariable Long accSeq, @RequestParam String password) {
        return new ResponseEntity(accountService.removeAccount(accSeq, password), HttpStatus.OK);
    }
}