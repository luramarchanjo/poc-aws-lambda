package com.example;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.ScheduledEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.commons.configuration2.EnvironmentConfiguration;

public class Function implements RequestHandler<ScheduledEvent, String> {

  private final EnvironmentConfiguration environment = new EnvironmentConfiguration();

  private final ObjectMapper objectMapper = new ObjectMapper();

  private final OkHttpClient client = new OkHttpClient.Builder().build();

  @SneakyThrows
  @Override
  public String handleRequest(ScheduledEvent event, Context context) {
    String endpoints = environment.get(String.class, "ENDPOINTS");
    EndpointList endpointList = objectMapper.readValue(endpoints, EndpointList.class);
    endpointList.getEndpoints().forEach(this::sendRequest);

    return context.getAwsRequestId();
  }

  private void sendRequest(String endpoint) {
    try {
      Request request = new Request.Builder().url(endpoint).build();
      Call call = client.newCall(request);
      Response response = call.execute();
      System.out.println("Sent request to " + endpoint + ", " + response);
    } catch (Exception e) {
      System.out.println("Fail to send request to "+ endpoint);
    }
  }

}