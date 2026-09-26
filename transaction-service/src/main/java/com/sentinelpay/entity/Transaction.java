package com.sentinelpay.entity;

import com.sentinelpay.enums.Status;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Entity

@EntityListeners( org.springframework.data.jpa.domain.support.AuditingEntityListener.class )

@Table(name = "transactions" , schema = "public")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder

public class Transaction {

    @Id
    private UUID id;

    private UUID accountId;

    private BigDecimal amount;

    private String currency;

    @Enumerated(EnumType.STRING)
    private Status status;

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

}
