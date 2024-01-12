FROM localstack/localstack:3.0.2

COPY scripts/01_configure.sh /etc/localstack/init/ready.d/01_configure.sh
COPY scripts/02_create_sns.sh /etc/localstack/init/ready.d/02_create_sns.sh
COPY scripts/03_create_sqs.sh /etc/localstack/init/ready.d/03_create_sqs.sh
COPY scripts/04_create_sns_subscriptions.sh /etc/localstack/init/ready.d/04_create_sns_subscriptions.sh