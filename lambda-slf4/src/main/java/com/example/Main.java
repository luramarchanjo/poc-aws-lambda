package com.example;

import com.amazonaws.serverless.proxy.internal.testutils.MockLambdaContext;
import com.amazonaws.services.lambda.runtime.events.ScheduledEvent;
import lombok.SneakyThrows;

public class Main {

  @SneakyThrows
  public static void main(String[] args) {
    ScheduledEvent scheduledEvent = new ScheduledEvent();
    new Function().handleRequest(scheduledEvent, new MockLambdaContext());
  }

}
