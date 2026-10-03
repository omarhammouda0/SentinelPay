package com.sentinelpay.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.time.Instant;
import java.util.UUID;

@Entity

@EntityListeners( org.springframework.data.jpa.domain.support.AuditingEntityListener.class )

@Table(name = "processed_events" , schema = "public")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder

public class Event {

    @Id
    private UUID id;

    Instant processedAt;

}
