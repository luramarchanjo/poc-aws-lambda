package com.example;

import com.amazonaws.serverless.proxy.internal.testutils.MockLambdaContext;
import com.amazonaws.services.lambda.runtime.events.SQSEvent;
import com.amazonaws.services.lambda.runtime.events.SQSEvent.SQSMessage;
import com.example.domain.Event;
import com.example.domain.Function;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Collections;
import java.util.UUID;
import lombok.SneakyThrows;

public class Main {

  @SneakyThrows
  public static void main(String[] args) {
    ObjectMapper objectMapper = new ObjectMapper();

    Event event = Event.builder()
        .id(UUID.randomUUID().toString())
        .content(UUID.randomUUID().toString())
        .correlationId(UUID.randomUUID().toString())
        .build();

    SQSMessage sqsMessage = new SQSMessage();
    sqsMessage.setBody(objectMapper.writeValueAsString(event));

    SQSEvent sqsEvent = new SQSEvent();
    sqsEvent.setRecords(Collections.singletonList(sqsMessage));

    new Function().handleRequest(sqsEvent, new MockLambdaContext());
  }

}