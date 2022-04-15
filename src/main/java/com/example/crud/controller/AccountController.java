package com.example.crud.controller;

import com.example.crud.entity.Account;
import com.example.crud.dto.AccountModifyRequestDto;
import com.example.crud.repository.AccountRepository;
import com.example.crud.service.AccountService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping("/api/v1/account")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;
    private final AccountRepository accountRepository;

    // 회원 개별 조회
    // Optional
    @ApiOperation(value = "회원 개별 조회", notes = "개별 회원 정보를 조회합니다.")
    @GetMapping("/{accSeq}")
    public ResponseEntity getOne(@PathVariable Long accSeq) {
        return new ResponseEntity<>(accountService.getOne(accSeq), HttpStatus.OK);
    }

    // 회원 전체 조회
    @ApiOperation(value = "회원 전체 조회", notes = "전체 회원 정보를 조회합니다.")
    @GetMapping("/all")
    public ResponseEntity getAll() {
        return new ResponseEntity<>(accountService.getAll(), HttpStatus.OK);
    }

    // 회원 가입
    @ApiOperation(value = "회원 생성", notes = "회원을 생성합니다.")
    @PostMapping
    public ResponseEntity create(@RequestBody Account account) {
        accountRepository.save(account);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    // 수정
    @ApiOperation(value = "회원 정보 수정", notes = "회원 정보를 수정합니다.")
    @PutMapping("/{accSeq}")
    public ResponseEntity update(@PathVariable Long accSeq, @RequestBody AccountModifyRequestDto request) {
        accountService.update(accSeq, request);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    // 삭제
    @ApiOperation(value = "회원 삭제", notes = "회원을 삭제합니다.")
    @DeleteMapping("/{accSeq}")
    public ResponseEntity delete(@PathVariable Long accSeq, @RequestParam String password) {
        accountService.delete(accSeq, password);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }
}
