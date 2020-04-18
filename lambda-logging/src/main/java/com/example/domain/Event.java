package com.example.domain;

import com.example.correlation.Correlation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Event implements Correlation {

  private String id;

  private String content;

  private String correlationId;

  @Override
  public String getCorrelationId() {
    return this.correlationId;
  }

}