package com.example;

import com.amazonaws.serverless.proxy.internal.testutils.MockLambdaContext;
import com.amazonaws.services.lambda.runtime.events.SQSEvent;
import com.amazonaws.services.lambda.runtime.events.SQSEvent.SQSMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import java.util.Objects;
import lombok.SneakyThrows;
import lombok.val;

public class Main {

  @SneakyThrows
  public static void main(String[] args) {
    val objectMapper = new ObjectMapper();

    val emailEvent = EmailEvent.builder()
        .to(Objects.requireNonNull(System.getenv("MAIL_TO"), "Environment MAIL_TO is missing"))
        .from(Objects.requireNonNull(System.getenv("MAIL_FROM"), "Environment MAIL_FROM is missing"))
        .subject("Email from lambda-mail-sender")
        .body("Testing lambda-mail-sender with AWS SES")
        .build();

    val sqsMessage = new SQSMessage();
    sqsMessage.setBody(objectMapper.writeValueAsString(emailEvent));

    val sqsEvent = new SQSEvent();
    sqsEvent.setRecords(List.of(sqsMessage));

    new Function().handleRequest(sqsEvent, new MockLambdaContext());
  }

}
