package com.kafka.app;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    @KafkaListener(topics = "string", groupId = "my-consumer-group")
    public void consume(String message) {
        System.out.println("Message received: " + message);
    }

    @KafkaListener(topics = "message", groupId = "my-consumer-group")
    public void consume(MyMessage message) {
        System.out.println("Received message: " + message);
    }
}
