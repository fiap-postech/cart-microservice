#!/bin/bash

echo "########### Creating sns subscriptions ###########"
aws --endpoint-url=http://127.0.0.1:4566 sns subscribe --profile=localstack --topic-arn arn:aws:sns:us-east-1:000000000000:local-cart-microservice-close-cart-topic --protocol sqs --notification-endpoint arn:aws:sqs:us-east-1:000000000000:local-payment-microservice-close-cart-queue --attributes RawMessageDelivery=true
aws --endpoint-url=http://127.0.0.1:4566 sns subscribe --profile=localstack --topic-arn arn:aws:sns:us-east-1:000000000000:local-cart-microservice-close-cart-topic --protocol sqs --notification-endpoint arn:aws:sqs:us-east-1:000000000000:local-order-microservice-close-cart-queue --attributes RawMessageDelivery=true
