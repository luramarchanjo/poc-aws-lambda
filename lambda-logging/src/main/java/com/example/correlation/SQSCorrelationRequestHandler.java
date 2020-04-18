package com.example.correlation;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.SQSEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.slf4j.MDC;

public interface SQSCorrelationRequestHandler<Input extends Correlation> extends RequestHandler<SQSEvent, Void> {

  ObjectMapper objectMapper = new ObjectMapper();

  @Override
  default Void handleRequest(SQSEvent sqsEvent, Context context) {
    sqsEvent.getRecords().forEach(sqsMessage -> {
      final Input input = convertBodyToInput(sqsMessage.getBody());
      MDC.put("correlationId", input.getCorrelationId());
      process(input);
    });
    return null;
  }

  @SneakyThrows
  default Input convertBodyToInput(String body) {
    return objectMapper.readValue(body, getInputClass());
  }

  @SneakyThrows
  Class<Input > getInputClass();

  @SneakyThrows
  void process(final Input input);

}