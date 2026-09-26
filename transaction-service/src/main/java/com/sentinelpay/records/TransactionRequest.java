package com.sentinelpay.records;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.util.UUID;


public record TransactionRequest(

        @NotNull( message = "Account ID is required" )
        UUID accountId ,

        @NotNull( message = "Amount is required" )
        @Positive ( message = "Amount must be greater than zero" )
        BigDecimal amount ,

        @NotBlank( message = "Currency is required" )
        @Size ( min = 3, max = 3, message = "Currency must be exactly 3 characters long" )
        String currency


) {
}
