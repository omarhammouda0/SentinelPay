package com.sentinelpay.service;

import com.sentinelpay.enums.RiskDecision;
import com.sentinelpay.enums.RiskReason;
import com.sentinelpay.event.RiskEvaluationResult;
import com.sentinelpay.event.TransactionCreatedEvent;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;


@Component
@Slf4j

public class RiskEvaluator {

    private static final BigDecimal threshold = BigDecimal.valueOf(5000);
    private static final List<String> acceptedCurrencies = List.of("EUR", "USD", "GBP");

    public RiskEvaluationResult evaluate (TransactionCreatedEvent transaction) {

        var amount = transaction.amount();
        var currency = transaction.currency();

        if (amount.compareTo ( threshold ) > 0) {
            log.info ( "Transaction {} is above the threshold of {}. Amount: {}", transaction.transactionId(), threshold, amount );
            return new RiskEvaluationResult(RiskDecision.REJECTED, RiskReason.AMOUNT_EXCEEDS_THRESHOLD);

        } else if (!acceptedCurrencies.contains(currency)) {
            log.info ( "Transaction {} has an unaccepted currency: {}", transaction.transactionId(), currency );
            return new RiskEvaluationResult( RiskDecision.REJECTED, RiskReason.CURRENCY_NOT_ACCEPTED);
        }

        return new RiskEvaluationResult(RiskDecision.APPROVED, RiskReason.NO_RISK);
    }


}
