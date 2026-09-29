package com.sentinelpay.event;

import com.sentinelpay.enums.RiskDecision;
import com.sentinelpay.enums.RiskReason;
import lombok.Builder;

@Builder

public record RiskEvaluationResult(

        RiskDecision decision,
        RiskReason reason

) {
}
