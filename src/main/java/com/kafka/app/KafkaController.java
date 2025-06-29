package com.kafka.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KafkaController {

  @Autowired
  private KafkaProducer kafkaProducer;

//  @PostMapping("/string")
//  public String sendMessage(@RequestBody String message) {
//    kafkaProducer.sendMessage(message);
//    return "Message sent!";
//  }

  @PostMapping("/message")
  public String sendMessage() {
    MyMessage message = new MyMessage("John", 25);
    kafkaProducer.sendMessage(message);
    return "Message sent!";
  }
}
