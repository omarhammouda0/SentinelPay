package com.sentinelpay.event;



import com.sentinelpay.enums.RiskDecision;
import com.sentinelpay.enums.RiskReason;
import lombok.Builder;

import java.time.Instant;
import java.util.UUID;

@Builder

public record RiskDecisionEvent(

        UUID eventId,
        UUID transactionId,
        RiskDecision decision,
        RiskReason reason,
        Instant createdAt



) {
}
