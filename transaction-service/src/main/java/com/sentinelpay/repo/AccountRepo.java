package com.sentinelpay.repo;

import com.sentinelpay.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AccountRepo extends JpaRepository<Account , UUID> {

}
