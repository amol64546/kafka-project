package com.kafka.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@EnableKafka
public class SpringProducer {

  @Autowired
  private KafkaTemplate<String, String> kafkaTemplate1;

  @Autowired
  private KafkaTemplate<String, MyMessage> kafkaTemplate2;

  public void sendMessage(String message) {
    kafkaTemplate1.send("string", message);
    System.out.println("Message sent: " + message);
  }


  public void sendMessage(MyMessage message) {
    kafkaTemplate2.send("message1", message);
  }
}
