package br.com.fiap.tech.challenge.customer.launcher.util;

import io.restassured.RestAssured;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.test.context.DynamicPropertyRegistry;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ConfigurationOverrides {

    private static final int LOCAL_PORT = 8690;

    public static void overrideConfiguration(DynamicPropertyRegistry registry, String mockServerEndpoint, String redisHost) {
        registry.add("customer.host", () -> mockServerEndpoint);
        registry.add("product.host", () -> mockServerEndpoint);

        registry.add("server.port", () -> String.valueOf(LOCAL_PORT));
        registry.add("spring.data.redis.host", () -> redisHost);
        registry.add("spring.data.redis.port", () -> 6380);

        RestAssured.port = LOCAL_PORT;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

}
