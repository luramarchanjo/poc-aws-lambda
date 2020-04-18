package com.example;

import com.amazonaws.serverless.proxy.internal.testutils.MockLambdaContext;
import com.amazonaws.services.lambda.runtime.events.SQSEvent;
import com.amazonaws.services.lambda.runtime.events.SQSEvent.SQSMessage;
import com.example.domain.Event;
import com.example.domain.Function;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import lombok.SneakyThrows;

public class Main {

  private static final ObjectMapper objectMapper = new ObjectMapper();

  @SneakyThrows
  public static void main(String[] args) {
    List<SQSMessage> sqsMessages = Arrays.asList(
        getRandomSqsMessage(),
        getRandomSqsMessage(),
        getRandomSqsMessage()
    );

    SQSEvent sqsEvent = new SQSEvent();
    sqsEvent.setRecords(sqsMessages);

    new Function().handleRequest(sqsEvent, new MockLambdaContext());
  }

  private static SQSMessage getRandomSqsMessage() throws JsonProcessingException {
    Event event = getRandomEvent();

    SQSMessage sqsMessage = new SQSMessage();
    sqsMessage.setBody(objectMapper.writeValueAsString(event));

    return sqsMessage;
  }

  private static Event getRandomEvent() {
    return Event.builder()
        .id(UUID.randomUUID().toString())
        .content(UUID.randomUUID().toString())
        .correlationId(UUID.randomUUID().toString())
        .build();
  }

}