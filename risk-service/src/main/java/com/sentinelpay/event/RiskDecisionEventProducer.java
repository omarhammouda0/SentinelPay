package com.sentinelpay.event;


import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class RiskDecisionEventProducer {

    private final KafkaTemplate<Object, Object> kafkaTemplate;

    public RiskDecisionEventProducer(KafkaTemplate<Object, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publishRiskDecisionEvent(RiskDecisionEvent event) {

        kafkaTemplate.send("risk.decision", event.transactionId ().toString(), event);

        log.info ( "Sending RiskDecisionEvent: {}" +
                ", With Transaction ID: {}" +
                ", Decision: {}" +
                ", Reason: {}" +
                ", Created At: {}" ,
                event.eventId ( ) ,
                event.transactionId ( ) ,
                event.decision ( ) ,
                event.reason ( ) ,
                event.createdAt ( ) );
    }
}
