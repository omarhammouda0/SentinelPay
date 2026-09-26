package com.sentinelpay.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.Instant;
import java.util.UUID;

@Entity

@EntityListeners( org.springframework.data.jpa.domain.support.AuditingEntityListener.class )

@Table(name = "accounts" , schema = "public")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder

public class Account {

    @Id
    private UUID id;

    private String status;

    @CreatedDate
    @Column (name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

}
