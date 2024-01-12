#!/bin/bash

echo "########### Creating profile ###########"

aws configure set aws_access_key_id laccess --profile=localstack
aws configure set aws_secret_access_key laccessSecret --profile=localstack
aws configure set region us-east-1 --profile=localstack

echo "########### Listing profile ###########"
aws configure list --profile=localstack

echo "########### Creating sns topics ###########"
aws --endpoint-url=http://127.0.0.1:4566 sns create-topic --profile=localstack --name local-cart-microservice-close-cart-topic &&

echo "########### Creating sqs queues ###########"
aws --endpoint-url=http://127.0.0.1:4566 sqs create-queue --profile=localstack --queue-name local-payment-microservice-close-cart-queue &&
aws --endpoint-url=http://127.0.0.1:4566 sqs create-queue --profile=localstack --queue-name local-order-microservice-close-cart-queue &&

echo "########### Creating sns subscriptions ###########"
aws --endpoint-url=http://127.0.0.1:4566 sns subscribe --profile=localstack --topic-arn arn:aws:sns:us-east-1:000000000000:local-cart-microservice-close-cart-topic --protocol sqs --notification-endpoint arn:aws:sqs:us-east-1:000000000000:local-payment-microservice-close-cart-queue --attributes RawMessageDelivery=true &&
aws --endpoint-url=http://127.0.0.1:4566 sns subscribe --profile=localstack --topic-arn arn:aws:sns:us-east-1:000000000000:local-cart-microservice-close-cart-topic --protocol sqs --notification-endpoint arn:aws:sqs:us-east-1:000000000000:local-order-microservice-close-cart-queue --attributes RawMessageDelivery=true
