package com.example.crud.repository;

import com.example.crud.dto.AccountReadResponseDto;
import com.example.crud.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    Optional<AccountReadResponseDto> findByAccSeq(Long accSeq);

    List<Account> findAll();

    Optional<Account> findByUserId(String userId);

    List<Account> findAllByUserId(String keyword);

}