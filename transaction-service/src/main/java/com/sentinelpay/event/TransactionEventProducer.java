package com.sentinelpay.event;

import lombok.AllArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@AllArgsConstructor

@Component
public class TransactionEventProducer {

    private final KafkaTemplate<Object, Object> kafkaTemplate;

    public void publishTransactionCreated(TransactionCreatedEvent event) {

        kafkaTemplate.send("transaction.created", event.accountId ().toString () , event);

        System.out.println
                ("Publishing TransactionCreatedEvent: " + event.eventId () +
                        ", With Transaction ID: " + event.transactionId () +
                        ", Account ID: " + event.accountId () +
                        ", Amount: " + event.amount () +
                        ", Currency: " + event.currency () +
                        ", Created At: " + event.createdAt ());
    }

}
