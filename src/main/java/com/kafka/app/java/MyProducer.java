package com.kafka.app.java;


import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

public class MyProducer {

    public static void main(String[] args) {

        Properties producerProps = new Properties();
        producerProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        producerProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        producerProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, IntegerSerializer.class);

        try (Producer<String, Integer> producer = new KafkaProducer<>(producerProps)) {
            for (int i=0; i<100; i++) {
                System.out.println("message: " + i);
                producer.send(new ProducerRecord<>("test", i));
            }
        } catch (Exception e) {
            e.getStackTrace();
        }


    }

}
