#!/bin/bash

echo "########### Creating sns topics ###########"
aws --endpoint-url=http://127.0.0.1:4566 sns create-topic --profile=localstack --name local-cart-microservice-close-cart-topic

