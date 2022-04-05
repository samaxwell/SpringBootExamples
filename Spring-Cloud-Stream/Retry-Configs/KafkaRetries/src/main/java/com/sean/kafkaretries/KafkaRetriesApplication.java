package com.sean.kafkaretries;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;

@Slf4j
@SpringBootApplication
public class KafkaRetriesApplication {
    private long delayStart = 0;
    public static void main(String[] args) {
        SpringApplication.run(KafkaRetriesApplication.class, args);
    }

    @Bean
    public java.util.function.Function<Message<String>, String> uppercase() {
        return message -> {
            log.info("Attempt {} after delay of {} seconds",
                    message.getHeaders().get("deliveryAttempt"),
                    delayStart == 0 ? 0  : (System.nanoTime() - delayStart) / 1_000_000_000);
            delayStart = System.nanoTime();
            return message.getPayload().toUpperCase();
//            throw new RuntimeException("Not this time ...");
        };
    }
}
