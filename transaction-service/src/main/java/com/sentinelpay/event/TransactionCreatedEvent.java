package com.sentinelpay.event;

import lombok.Builder;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Builder

public record TransactionCreatedEvent(

        UUID eventId ,
        UUID transactionId ,
        UUID accountId ,
        BigDecimal amount ,
        String currency ,
        Instant createdAt


) {
}
