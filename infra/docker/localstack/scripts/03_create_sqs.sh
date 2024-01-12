#!/bin/bash

echo "########### Creating sqs queues ###########"
aws --endpoint-url=http://127.0.0.1:4566 sqs create-queue --profile=localstack --queue-name local-payment-microservice-close-cart-queue
aws --endpoint-url=http://127.0.0.1:4566 sqs create-queue --profile=localstack --queue-name local-order-microservice-close-cart-queue