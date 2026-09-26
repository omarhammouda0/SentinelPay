package com.sentinelpay.service;

import com.sentinelpay.entity.Transaction;
import com.sentinelpay.enums.Status;
import com.sentinelpay.records.TransactionRequest;
import com.sentinelpay.repo.AccountRepo;
import com.sentinelpay.repo.TransactionRepo;
import com.sentinelpay.records.TransactionResponse;

import lombok.AllArgsConstructor;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
@Log4j2


public class TransactionService {

    private final TransactionRepo transactionRepo;
    private final AccountRepo accountRepo;

    public void validateTransaction(TransactionRequest transaction) {

        UUID accountId =  transaction.accountId () ;

        BigDecimal amount = transaction.amount ( ) ;


       if (!accountRepo.existsById(accountId)) {

            log.error("Account with ID {} not found", accountId);
            throw new RuntimeException("Account not found");
        }

        if (amount.compareTo(BigDecimal.ZERO) <= 0) {

            log.error("Transaction must have an amount greater than zero");

            throw new RuntimeException("Amount must be greater than zero");

        }

    }

    public TransactionResponse createTransaction (TransactionRequest transaction) {

        validateTransaction(transaction);

        var newTransaction = Transaction.builder()

                .id(UUID.randomUUID())
                .accountId(transaction.accountId ())
                .amount(transaction.amount ())
                .currency(transaction.currency ().toUpperCase ())
                .status ( Status.PENDING_RISK )

                .build();

        var savedTransaction =  transactionRepo.save(newTransaction);
        log.info("Transaction with ID {} created successfully", newTransaction.getId());

        return new TransactionResponse(
                savedTransaction.getId(),
                savedTransaction.getStatus(),
                savedTransaction.getCreatedAt()
        );

    }

    public TransactionResponse getTransactionById(UUID transactionId) {


        var transaction = transactionRepo.findById(transactionId).orElseThrow(()
                -> new RuntimeException("Transaction with ID " + transactionId + " not found"));

        return new TransactionResponse(
                transaction.getId(),
                transaction.getStatus(),
                transaction.getCreatedAt()
        );
    }

    public List<Transaction> getAllTransactions() {
        return transactionRepo.findAll ();
    }
}
