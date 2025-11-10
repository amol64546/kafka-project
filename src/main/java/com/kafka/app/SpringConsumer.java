package com.kafka.app;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class SpringConsumer {

    @KafkaListener(
            topics = "string",
            groupId = "group-1",
            containerFactory = "kafkaListenerContainerFactory1"
    )
    public void consume(String message) {
        System.out.println("Message received: " + message);
    }

    @KafkaListener(
            topics = "message1",
            groupId = "group-2",
            containerFactory = "kafkaListenerContainerFactory2"
    )
    public void consume(MyMessage message) {
        System.out.println("Received message: " + message);
    }
}
