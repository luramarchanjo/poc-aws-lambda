# Overview

This is a poc using [AWS Lambda Logging](https://aws.amazon.com/lambda/), 
[Correlation Id](https://dzone.com/articles/correlation-id-for-logging-in-microservices) and [Logack MDC](http://logback.qos.ch/manual/mdc.html) to improve your Observability!

# Testing

To test your function run the `Main.class`

# Samples

```
[b0e0770d-c47e-494c-810c-051f4ad21116][com.example.domain.Function] - Received Event(id=c20098e1-acf1-4c8f-93bf-2397abe818bd, content=c9a70d58-0efb-46be-92ee-043e27e1aec0, correlationId=b0e0770d-c47e-494c-810c-051f4ad21116)
[b0e0770d-c47e-494c-810c-051f4ad21116][com.example.domain.Function] - Processed Event(id=c20098e1-acf1-4c8f-93bf-2397abe818bd, content=c9a70d58-0efb-46be-92ee-043e27e1aec0, correlationId=b0e0770d-c47e-494c-810c-051f4ad21116)
[b0e0770d-c47e-494c-810c-051f4ad21116][com.example.domain.Function] - Saved Event(id=c20098e1-acf1-4c8f-93bf-2397abe818bd, content=c9a70d58-0efb-46be-92ee-043e27e1aec0, correlationId=b0e0770d-c47e-494c-810c-051f4ad21116)
```

# Abstractions 

[Correlation](https://github.com/larchanjo/poc-aws-lambda/blob/master/lambda-logging/src/main/java/com/example/correlation/Correlation.java)

[SQSCorrelationRequestHandler](https://github.com/larchanjo/poc-aws-lambda/blob/master/lambda-logging/src/main/java/com/example/correlation/SQSCorrelationRequestHandler.java)

# Implementations

[Event](https://github.com/larchanjo/poc-aws-lambda/blob/master/lambda-logging/src/main/java/com/example/domain/Event.java)

[Function](https://github.com/larchanjo/poc-aws-lambda/blob/master/lambda-logging/src/main/java/com/example/domain/Function.java)

# Be Happy
