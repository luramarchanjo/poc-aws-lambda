package com.example.domain;

import com.example.correlation.SQSCorrelationRequestHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Function implements SQSCorrelationRequestHandler<Event> {

  private static final Logger logger = LoggerFactory.getLogger(Function.class);

  @Override
  public Class<Event> getInputClass() {
    return Event.class;
  }

  @Override
  public void process(Event event) {
    logger.info("Received {}", event);
    logger.info("Processed {}", event);
    logger.info("Saved {}", event);
    logger.info("--------------------------------------------------------------------------------");
  }

}