package com.kafka.app;

import java.util.HashMap;
import java.util.Map;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

@Configuration
@EnableKafka
public class KafkaConfig {

  @Bean
  public KafkaTemplate<String, MyMessage> kafkaTemplate() {
    return new KafkaTemplate<>(producerFactory());
  }

  @Bean
  public ProducerFactory<String, MyMessage> producerFactory() {
    Map<String, Object> configProps = new HashMap<>();
    configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
    configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
    configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);

//    configProps.put(JsonSerializer.ADD_TYPE_INFO_HEADERS, true);  // Optional
//    configProps.put(JsonSerializer.TYPE_MAPPINGS, "MyMessage=com.kafka.app.MyMessage");

    JsonSerializer<MyMessage> jsonSerializer = new JsonSerializer<>();
    return new DefaultKafkaProducerFactory<>(configProps, new StringSerializer(), jsonSerializer);
  }

  @Bean
  public ConsumerFactory<String, MyMessage> consumerFactory() {
    Map<String, Object> configProps = new HashMap<>();
    configProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
    configProps.put(ConsumerConfig.GROUP_ID_CONFIG, "my-consumer-group");
    configProps.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

    // Configure deserializers
    configProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
    configProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);


    // CRITICAL: Configure JsonDeserializer properly
//    configProps.put(JsonDeserializer.TRUSTED_PACKAGES, "*"); // Trust all packages (or use "com.kafka.app")
//    configProps.put(JsonDeserializer.VALUE_DEFAULT_TYPE, "com.kafka.app.MyMessage");

    JsonDeserializer<MyMessage> jsonDeserializer = new JsonDeserializer<>(MyMessage.class);
    jsonDeserializer.addTrustedPackages("com.kafka.app"); // Specify trusted packages

    // Create factory with explicit deserializer configuration
    return new DefaultKafkaConsumerFactory<>(configProps, new StringDeserializer(), jsonDeserializer);
  }

}
