package com.sentinelpay.service;

import com.sentinelpay.event.RiskDecisionEvent;
import com.sentinelpay.event.TransactionCreatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.UUID;

@Slf4j
@Component

public class TransactionEventConsumer {

    private final RiskEvaluator riskEvaluator;
    private final RiskDecisionEventProducer riskDecisionEventProducer;

    public TransactionEventConsumer(RiskEvaluator riskEvaluator , RiskDecisionEventProducer riskDecisionEventProducer) {
        this.riskEvaluator = riskEvaluator;
        this.riskDecisionEventProducer = riskDecisionEventProducer;
    }

    @KafkaListener(topics = "transaction.created")

    public void consumeTransactionCreated(TransactionCreatedEvent event) {

        log.info ( "Consuming TransactionCreatedEvent: {}, Transaction ID: {}" , event.eventId ( ) , event.transactionId ( ) );


        var result = riskEvaluator.evaluate ( event );
        var decision = result.decision();
        var reason = result.reason();

        var riskDecisionEvent = RiskDecisionEvent.builder ()

                .eventId ( UUID.randomUUID () )
                .transactionId ( event.transactionId ( ) )
                .decision ( decision )
                .reason ( reason )
                .createdAt ( Instant.now () )

                .build ( );


        log.info ( "Transaction {} has been {} with reason: {}" , event.transactionId ( ) , decision , reason );

        riskDecisionEventProducer.publishRiskDecisionEvent ( riskDecisionEvent );

    }
}
