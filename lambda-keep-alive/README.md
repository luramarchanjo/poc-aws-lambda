# Overview

This is a poc using [AWS Lambda](https://aws.amazon.com/lambda/)

# Setup AWS

* [IAM](https://docs.aws.amazon.com/sdk-for-java/v1/developer-guide/setup-credentials.html)
* [AWS Simple Email Service (SES)](https://aws.amazon.com/ses/)
* [AWS Simple Queue Service (SQS)](https://aws.amazon.com/sqs/)
* [AWS Lambda](https://aws.amazon.com/lambda/)

# Testing locally

To test locally you need to run the class `Main`, but before, you need to configure the environments:

* MAIL_TO
* MAIL_FROM

```
If you are using Simple Email Service (SES) SANDBOX both *EMAILS* must be validated
```

# Testing on Amazon Web Services (AWS)

## Setup

#### [AWS Simple Email Service (SES)](https://aws.amazon.com/ses/)

Verify some personal email addresses

#### [AWS Simple Queue Service (SQS)](https://aws.amazon.com/sqs/)

First, create a dead letter queue named `mail-ddl.fifo`

Second, create fifo queue called `mail.fifo`

#### [AWS Lambda](https://aws.amazon.com/lambda/)

First, create a lambda called `lambda-mail-sender`

Second, attach these policies `AWSLambdaSQSQueueExecutionRole` and `AmazonSNSFullAccess` to the 
lambda role

Third, add SQS trigger to the lambda

## Testing

Go to the SQS and send a message with this payload:

#### [EmailEvent](https://github.com/larchanjo/poc-aws-lambda/blob/master/lambda-mail-sender/src/main/java/com/example/EmailEvent.java)

```
{
    "to" : "string",
    "from" : "string",
    "subject" : "string",
    "body" : "string",
    "htmlBody" : true
}
```

# Be Happy