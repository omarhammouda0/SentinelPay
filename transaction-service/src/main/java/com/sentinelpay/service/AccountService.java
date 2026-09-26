package com.sentinelpay.service;

import com.sentinelpay.entity.Account;
import com.sentinelpay.repo.AccountRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor

public class AccountService {

    private final AccountRepo accountRepo;

    public Account createAccount(Account account) {

        var newAccount = Account.builder ( ).id ( UUID.randomUUID ( ) )
                .status ( account.getStatus ( ) )
                .build ( );

        return accountRepo.save ( newAccount );
    }

    public Account getAccountById(UUID accountId) {

        return accountRepo.findById(accountId).orElseThrow(
                () -> new RuntimeException("Account not found"));

    }

    public List<Account> getAllAccounts() {
        return accountRepo.findAll();
    }
}
