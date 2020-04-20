package com.example;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.SQSEvent;
import com.amazonaws.services.lambda.runtime.events.SQSEvent.SQSMessage;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;
import com.amazonaws.services.simpleemail.model.Body;
import com.amazonaws.services.simpleemail.model.Content;
import com.amazonaws.services.simpleemail.model.Destination;
import com.amazonaws.services.simpleemail.model.Message;
import com.amazonaws.services.simpleemail.model.SendEmailRequest;
import com.amazonaws.services.simpleemail.model.SendEmailResult;
import com.amazonaws.xray.AWSXRay;
import com.amazonaws.xray.handlers.TracingHandler;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Random;
import lombok.NonNull;
import lombok.SneakyThrows;
import lombok.val;

public class Function implements RequestHandler<SQSEvent, String> {

  private final AmazonSimpleEmailService sesService = AmazonSimpleEmailServiceClientBuilder
      .standard()
      .withRequestHandlers(new TracingHandler(AWSXRay.getGlobalRecorder()))
      .build();

  private final ObjectMapper objectMapper = new ObjectMapper();

  private final boolean simulateError = Boolean.parseBoolean(System.getenv("SIMULATE_ERROR"));

  private final boolean simulateRandomError = Boolean
      .parseBoolean(System.getenv("SIMULATE_RANDOM_ERROR"));

  @Override
  public String handleRequest(SQSEvent sqsEvent, Context context) {
    val logger = context.getLogger();
    if (simulateError) {
      logger.log("Simulate error flag is enabled...simulating error");
      throw new RuntimeException("Random error " + context.getAwsRequestId());
    }

    if (simulateRandomError && generateRandomNumber(1, 10) > 5) {
      logger.log("Simulate error flag is enabled...simulating error");
      throw new RuntimeException("Random error " + context.getAwsRequestId());
    }

    Optional.of(sqsEvent.getRecords())
        .orElse(new ArrayList<>())
        .forEach(message -> processMessage(message, logger));

    return context.getAwsRequestId();
  }

  @SneakyThrows
  private void processMessage(@NonNull SQSMessage sqsMessage, @NonNull LambdaLogger logger) {
    logger.log(String.format("Processing %s", sqsMessage));
    val emailEvent = objectMapper.readValue(sqsMessage.getBody(), EmailEvent.class);

    Destination destination = new Destination().withToAddresses(emailEvent.getTo());
    Content subjectContent = new Content().withCharset("UTF-8").withData(emailEvent.getSubject());
    Content bodyContent = new Content().withCharset("UTF-8").withData(emailEvent.getBody());

    Body body;
    if (emailEvent.isHtmlBody()) {
      body = new Body().withHtml(bodyContent);
    } else {
      body = new Body().withText(bodyContent);
    }

    Message message = new Message().withBody(body).withSubject(subjectContent);

    SendEmailRequest request = new SendEmailRequest()
        .withDestination(destination)
        .withSource(emailEvent.getFrom())
        .withMessage(message);

    SendEmailResult response = sesService.sendEmail(request);
    logger.log(String.format("Processed %s %s", sqsMessage, response));
  }

  private int generateRandomNumber(@NonNull int min, @NonNull int max) {
    val random = new Random();
    return random.nextInt(max - min + 1) + min;
  }

}