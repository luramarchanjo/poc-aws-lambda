# Overview

This is a poc using [AWS SDK](https://aws.amazon.com/sdk-for-java/) and [AWS SQS (Simple Queue Service)](https://aws.amazon.com/sqs/).

# Setup AWS

* [IAM](https://docs.aws.amazon.com/sdk-for-java/v1/developer-guide/setup-credentials.html)
* [AWS SQS (Simple Queue Service)](https://docs.aws.amazon.com/AWSSimpleQueueService/latest/SQSDeveloperGuide/sqs-setting-up.html)

# Setup Application

You need to configure the following environment variables:

* AWS_ACCESS_KEY_ID
* AWS_SECRET_ACCESS_KEY

# Testing Standard Queue

Run the `StandardProducer.class` then `StandardConsumer.class`

# Testing FIFO Queue

Run the `FifoProducer.class` then `FifoConsumer.class`

# Be Happy