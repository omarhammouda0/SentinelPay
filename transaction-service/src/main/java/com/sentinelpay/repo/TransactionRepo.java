package com.sentinelpay.repo;

import com.sentinelpay.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository

public interface TransactionRepo extends JpaRepository<Transaction, UUID> {

}
