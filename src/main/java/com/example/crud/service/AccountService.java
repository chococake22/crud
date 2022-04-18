package com.example.crud.service;

import com.example.crud.dto.*;
import com.example.crud.entity.Account;
import com.example.crud.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class AccountService {

    private final AccountRepository accountRepository;

    // 트랜잭션 필요 없음  (단순 조회의 경우 추가적인 로직이 필요없어서 굳이 리소스를 소모할 이유가 없다)
    // 그래서 트랜잭션이 필요한 부분과 아닌 부분이 있어서 어노테이션을 클래스가 아닌 메소드 단위로 사용한다.

    // 개별 회원 조회
    @Transactional
    public ResponseEntity<AccountReadResponseDto> getAccountInfoOne(Long accSeq) {

        try {
            Account account = Optional.ofNullable(accountRepository.getById(accSeq))
                    .orElseThrow(IllegalArgumentException::new);

            if (account != null) {
                AccountReadResponseDto accountReadResponseDto = AccountReadResponseDto.builder()
                        .userId(account.getUserId())
                        .password(account.getPassword())
                        .email(account.getEmail())
                        .phone(account.getPhone())
                        .build();

                return ResponseEntity.status(HttpStatus.OK).body(accountReadResponseDto);
            } else {
                throw new IllegalArgumentException("존재하지 않습니다.");
            }
        } catch (Exception e) {
            log.info(e.getMessage());
        }

        return new ResponseEntity("존재하지 않는 회원입니다.", HttpStatus.BAD_REQUEST);
    }

    // 전체 회원 정보
    @Transactional
    public ResponseEntity<List<AccountReadResponseDto>> getAccountInfoAll() {

        List<Account> accountList = accountRepository.findAll();

        List<AccountReadResponseDto> accountReadResponseDtoList
                = accountList.stream()
                .map(account -> new AccountReadResponseDto(
                        account.getUserId(),
                        account.getPassword(),
                        account.getEmail(),
                        account.getPhone()
                )).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(accountReadResponseDtoList);
    }

    // 회원 검색
    @Transactional
    public ResponseEntity<List<AccountSearchResponseDto>> searchAccount(String keyword) {

        List<Account> accountList = accountRepository.findAllByUserId(keyword);

        List<AccountSearchResponseDto> accountSearchResponseDtoList
                = accountList.stream()
                .map(account -> new AccountSearchResponseDto(
                        account.getUserId(),
                        account.getPassword(),
                        account.getEmail(),
                        account.getPhone()
                )).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(accountSearchResponseDtoList);
    }

    // 회원 수정
    @Transactional
    public ResponseEntity<AccountModifyRequestDto> modifyAccountInfo(Long accSeq, AccountModifyRequestDto request) {

        Account account = Optional.ofNullable(accountRepository.getById(accSeq))
                .orElseThrow(IllegalArgumentException::new);

        account.update(request);

        return ResponseEntity.status(HttpStatus.OK).body(request);
    }

    // 가입에 필요한 정보만 가져와서 가입하도록 Request 객체 생성
    @Transactional
    public ResponseEntity<AccountCreateRequestDto> addAccount(AccountCreateRequestDto request) {

        Account account;

        try {
            if (!accountRepository.findByUserId(request.getUserId()).isPresent()) {
                account = Account.builder()
                        .userId(request.getUserId())
                        .password(request.getPassword())
                        .email(request.getEmail())
                        .phone(request.getPhone())
                        .build();

                accountRepository.save(account);

                return ResponseEntity.status(HttpStatus.OK).body(request);
            } else {
                throw new IllegalArgumentException("이미 있는 사용자입니다.");
            }
        } catch (Exception e) {
            log.info(e.getMessage());
        }
        return new ResponseEntity("이미 존재하는 아이디입니다.", HttpStatus.BAD_REQUEST);
    }

    // 삭제
    @Transactional
    public ResponseEntity removeAccount(Long accSeq, String password) {
        Account account = Optional.ofNullable(accountRepository.getById(accSeq))
                .orElseThrow(IllegalArgumentException::new);

        // 비밀번호 확인 후 삭제
        try {
            if(account.getPassword().equals(password)) {
                accountRepository.deleteById(accSeq);
                return new ResponseEntity("회원을 삭제했습니다.", HttpStatus.OK);
            } else {
                throw new IllegalArgumentException("비밀번호가 틀렸습니다.");
            }

        } catch (Exception e) {
            log.info(e.getMessage());
        }

        return new ResponseEntity("비밀번호가 틀렸습니다.", HttpStatus.BAD_REQUEST);
    }
}