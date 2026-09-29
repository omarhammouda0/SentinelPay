package com.sentinelpay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing

public class RiskServiceApplication {

        public static void main(String[] args) {
            SpringApplication.run(RiskServiceApplication.class, args);
        }
}