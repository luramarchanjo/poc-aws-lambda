package com.example;

import java.util.ArrayList;
import java.util.Collection;
import lombok.Data;

@Data
public class EndpointList {

  private Collection<String> endpoints = new ArrayList<>();

}