# Overview

This is a poc using [AWS Lambda Logging](https://aws.amazon.com/lambda/), 
[Correlation Id](https://dzone.com/articles/correlation-id-for-logging-in-microservices) and [Logack MDC](http://logback.qos.ch/manual/mdc.html) to improve your Observability!

# Testing

To test your function run the `Main.class`

# Samples

```
[main][INFO ][2020-04-18 13:06:38][b0e0770d-c47e-494c-810c-051f4ad21116][com.example.domain.Function] - Received Event(id=c20098e1-acf1-4c8f-93bf-2397abe818bd, content=c9a70d58-0efb-46be-92ee-043e27e1aec0, correlationId=b0e0770d-c47e-494c-810c-051f4ad21116)
[main][INFO ][2020-04-18 13:06:38][b0e0770d-c47e-494c-810c-051f4ad21116][com.example.domain.Function] - Processed Event(id=c20098e1-acf1-4c8f-93bf-2397abe818bd, content=c9a70d58-0efb-46be-92ee-043e27e1aec0, correlationId=b0e0770d-c47e-494c-810c-051f4ad21116)
[main][INFO ][2020-04-18 13:06:38][b0e0770d-c47e-494c-810c-051f4ad21116][com.example.domain.Function] - Saved Event(id=c20098e1-acf1-4c8f-93bf-2397abe818bd, content=c9a70d58-0efb-46be-92ee-043e27e1aec0, correlationId=b0e0770d-c47e-494c-810c-051f4ad21116)
[main][INFO ][2020-04-18 13:06:38][b0e0770d-c47e-494c-810c-051f4ad21116][com.example.domain.Function] - --------------------------------------------------------------------------------
[main][INFO ][2020-04-18 13:06:38][dc11c124-7812-482b-9845-8848c998fc31][com.example.domain.Function] - Received Event(id=483ad565-a648-45a6-affa-75826515e077, content=b6a7aa71-aec0-439f-a584-3b49b3860846, correlationId=dc11c124-7812-482b-9845-8848c998fc31)
[main][INFO ][2020-04-18 13:06:38][dc11c124-7812-482b-9845-8848c998fc31][com.example.domain.Function] - Processed Event(id=483ad565-a648-45a6-affa-75826515e077, content=b6a7aa71-aec0-439f-a584-3b49b3860846, correlationId=dc11c124-7812-482b-9845-8848c998fc31)
[main][INFO ][2020-04-18 13:06:38][dc11c124-7812-482b-9845-8848c998fc31][com.example.domain.Function] - Saved Event(id=483ad565-a648-45a6-affa-75826515e077, content=b6a7aa71-aec0-439f-a584-3b49b3860846, correlationId=dc11c124-7812-482b-9845-8848c998fc31)
[main][INFO ][2020-04-18 13:06:38][dc11c124-7812-482b-9845-8848c998fc31][com.example.domain.Function] - --------------------------------------------------------------------------------
[main][INFO ][2020-04-18 13:06:38][916bc074-15b6-4539-8b25-24811d95563c][com.example.domain.Function] - Received Event(id=dfb76ebc-6eca-4512-a9d3-0396c3a54239, content=9d38e644-616e-4ba9-aa04-8a595642ed6d, correlationId=916bc074-15b6-4539-8b25-24811d95563c)
[main][INFO ][2020-04-18 13:06:38][916bc074-15b6-4539-8b25-24811d95563c][com.example.domain.Function] - Processed Event(id=dfb76ebc-6eca-4512-a9d3-0396c3a54239, content=9d38e644-616e-4ba9-aa04-8a595642ed6d, correlationId=916bc074-15b6-4539-8b25-24811d95563c)
[main][INFO ][2020-04-18 13:06:38][916bc074-15b6-4539-8b25-24811d95563c][com.example.domain.Function] - Saved Event(id=dfb76ebc-6eca-4512-a9d3-0396c3a54239, content=9d38e644-616e-4ba9-aa04-8a595642ed6d, correlationId=916bc074-15b6-4539-8b25-24811d95563c)
[main][INFO ][2020-04-18 13:06:38][916bc074-15b6-4539-8b25-24811d95563c][com.example.domain.Function] - --------------------------------------------------------------------------------
```

# Abstractions 

[Correlation](https://github.com/larchanjo/poc-aws-lambda/blob/master/lambda-logging/src/main/java/com/example/correlation/Correlation.java)

[SQSCorrelationRequestHandler](https://github.com/larchanjo/poc-aws-lambda/blob/master/lambda-logging/src/main/java/com/example/correlation/SQSCorrelationRequestHandler.java)

# Implementations

[Event](https://github.com/larchanjo/poc-aws-lambda/blob/master/lambda-logging/src/main/java/com/example/domain/Event.java)

[Function](https://github.com/larchanjo/poc-aws-lambda/blob/master/lambda-logging/src/main/java/com/example/domain/Function.java)

# Be Happy
