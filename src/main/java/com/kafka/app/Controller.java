package com.kafka.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

  @Autowired
  private SpringProducer springProducer;

  @PostMapping("/string")
  public String sendMessage(@RequestBody String message) {
    springProducer.sendMessage(message);
    return "Message sent!";
  }

  @PostMapping("/message")
  public String sendMessage(@RequestBody MyMessage message) {
    springProducer.sendMessage(message);
    return "Message sent!";
  }
}
