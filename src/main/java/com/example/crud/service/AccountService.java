package com.example.crud.service;

import com.example.crud.entity.Account;
import com.example.crud.dto.AccountModifyRequestDto;
import com.example.crud.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;

    @Transactional  // 트랜잭션 필요 없음  (단순 조회의 경우 추가적인 로직이 필요없어서 굳이 리소스를 소모할 이유가 없다)
    public Optional<Account> getOne(Long accSeq) {
        Optional<Account> account = accountRepository.findById(accSeq);
        return account;
    }

    @Transactional
    public List<Account> getAll() {
        return accountRepository.findAll();
    }

    @Transactional
    public void update(Long accSeq, AccountModifyRequestDto request) {

        Account account = Optional.ofNullable(accountRepository.getById(accSeq))
                .orElseThrow(IllegalArgumentException::new);

        account.update(request);
    }

    @Transactional
    public void delete(Long accSeq, String password) {
        Account account = Optional.ofNullable(accountRepository.getById(accSeq))
                .orElseThrow(IllegalArgumentException::new);

        // 비밀번호 확인 후 삭제
        if(account.getPassword().equals(password)) {
            accountRepository.deleteById(accSeq);
        }
    }
}
