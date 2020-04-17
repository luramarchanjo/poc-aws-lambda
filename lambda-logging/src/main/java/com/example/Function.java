package com.example;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.ScheduledEvent;
import java.util.UUID;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

public class Function implements RequestHandler<ScheduledEvent, String> {

  private static final Logger logger = LoggerFactory.getLogger(Function.class);

  @SneakyThrows
  @Override
  public String handleRequest(ScheduledEvent event, Context context) {
    MDC.put("eventId", UUID.randomUUID().toString());
    logger.info("Testing lambda");
    return context.getAwsRequestId();
  }

}