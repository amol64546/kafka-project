package com.kafka.app.java;

import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.serialization.IntegerDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

public class MyConsumer {
    public static void main(String[] args) {

        Properties consumerProps = new Properties();
        consumerProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        consumerProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        consumerProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, IntegerDeserializer.class);
        consumerProps.put(ConsumerConfig.GROUP_ID_CONFIG, "group-1");

        try (Consumer<String, Integer> consumer = new KafkaConsumer<>(consumerProps)) {
            consumer.subscribe(Collections.singletonList("test"));
            while (!Thread.interrupted()) {
                ConsumerRecords<String, Integer> records = consumer.poll(Duration.ofMillis(100));
                for (ConsumerRecord<String, Integer> record : records) {
                    System.out.println("message: " + record.value());
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
