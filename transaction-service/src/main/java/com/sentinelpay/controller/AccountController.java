package com.sentinelpay.controller;

import com.sentinelpay.entity.Account;
import com.sentinelpay.service.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/accounts")

@AllArgsConstructor

public class AccountController {

    private final AccountService accountService;

    @PostMapping
    public Account createAccount(@RequestBody Account account) {
        return accountService.createAccount ( account );

    }


    @GetMapping("/{accountId}")
    public Account getAccountById(@PathVariable UUID accountId) {
        return accountService.getAccountById(accountId);
    }

    @GetMapping
    public List <Account> getAllAccounts() {
        return accountService.getAllAccounts();
    }
}
