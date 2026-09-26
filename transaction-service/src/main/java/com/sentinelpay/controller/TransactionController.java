package com.sentinelpay.controller;

import com.sentinelpay.entity.Transaction;
import com.sentinelpay.records.TransactionRequest;
import com.sentinelpay.records.TransactionResponse;
import com.sentinelpay.service.TransactionService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/transactions")

@AllArgsConstructor

public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping
    public TransactionResponse createTransaction(@Valid @RequestBody TransactionRequest transaction) {
        return transactionService.createTransaction ( transaction );

    }

    @GetMapping("/{transactionId}")
    public TransactionResponse getTransactionById(@PathVariable UUID transactionId) {
        return transactionService.getTransactionById ( transactionId );
    }

    @GetMapping
    public List<TransactionResponse> getAllTransactions() {
        return transactionService.getAllTransactions()
                .stream ()
                .map ( transaction -> new TransactionResponse ( transaction.getId(), transaction.getStatus(), transaction.getCreatedAt() ) )
                .toList ();
    }
}
