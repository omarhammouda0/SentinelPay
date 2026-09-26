package com.sentinelpay.records;

import com.sentinelpay.enums.Status;

import java.time.Instant;
import java.util.UUID;

public record TransactionResponse
        (UUID id, Status status , Instant createdAt)
{
}
