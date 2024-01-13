FROM --platform=linux/amd64 openjdk:17-alpine

WORKDIR /service

COPY ./cart-service.jar ./cart-service.jar

RUN /bin/sh -c 'touch /service/cart-service.jar'

CMD ["java", "-jar", "cart-service.jar"]