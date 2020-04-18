package com.example.correlation;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.SQSEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;

public interface SQSCorrelationRequestHandler<Input extends Correlation> extends
    RequestHandler<SQSEvent, Void> {

  ObjectMapper objectMapper = new ObjectMapper();

  @Override
  default Void handleRequest(SQSEvent sqsEvent, Context context) {
    sqsEvent.getRecords().forEach(sqsMessage -> {
      final Input input = convertBodyToInput(sqsMessage.getBody());

      final String correlationId = input.getCorrelationId();

      if (StringUtils.isNotBlank(correlationId)) {
        MDC.put("correlationId", correlationId);
        process(input);
      } else {
        final String errorMessage = String.format("Invalid correlationId=[%s] class=[%s]",
            correlationId, input.getClass().getName());
        throw new UnsupportedOperationException(errorMessage);
      }
    });

    return null;
  }

  @SneakyThrows
  default Input convertBodyToInput(String body) {
    return objectMapper.readValue(body, getInputClass());
  }

  Class<Input> getInputClass();

  void process(final Input input);

}