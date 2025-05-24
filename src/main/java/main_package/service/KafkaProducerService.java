package main_package.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {

  private final KafkaTemplate<String, String> kafkaTemplate;

  private final ObjectMapper objectMapper;
  private final String action;

  public KafkaProducerService(
      KafkaTemplate<String, String> kafkaTemplate,
      ObjectMapper objectMapper,
      @Value("${action-to-send}") String action) {
    this.kafkaTemplate = kafkaTemplate;
    this.objectMapper = objectMapper;
    this.action = action;
  }

  public void sendAction(Long id, String action) throws JsonProcessingException {
    String message = objectMapper.writeValueAsString(id + " - " + action);
    CompletableFuture<SendResult<String, String>> sendResult = kafkaTemplate.send(topic, message);
  }
}
