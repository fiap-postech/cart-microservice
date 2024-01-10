package br.com.fiap.tech.challenge.cart.launcher.container;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.containers.Network;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.utility.DockerImageName;

import java.time.Duration;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MicroserviceContainers {

    public static GenericContainer<?> customerServiceContainer(Network network, MySQLContainer<?> database){
        return new GenericContainer<>(DockerImageName.parse("fiapsoat2grupo13/customer-service:latest"))
                .withEnv("spring.profiles.active", "local")
                .withEnv("spring.datasource.url", "jdbc:mysql://host.docker.internal:33996/customer")
                .withEnv("spring.datasource.username", "sys_customer")
                .withEnv("spring.datasource.password", "sys_customer")
                .withEnv("spring.jpa.open-in-view", "false")
                .withNetwork(network)
                .waitingFor(
                        Wait.forHttp("/monitor/health")
                                .forStatusCode(200)
                                .withStartupTimeout(Duration.ofMinutes(3))
                )
                .dependsOn(database);
    }

    public static GenericContainer<?> productServiceContainer(Network network, MySQLContainer<?> database){
        return new GenericContainer<>(DockerImageName.parse("fiapsoat2grupo13/product-service:latest"))
                .withEnv("spring.profiles.active", "local")
                .withEnv("spring.datasource.url", "jdbc:mysql://host.docker.internal:33996/product")
                .withEnv("spring.datasource.username", "sys_product")
                .withEnv("spring.datasource.password", "sys_product")
                .withEnv("spring.jpa.open-in-view", "false")
                .withNetwork(network)
                .waitingFor(
                        Wait.forHttp("/monitor/health")
                                .forStatusCode(200)
                                .withStartupTimeout(Duration.ofMinutes(3))
                )
                .dependsOn(database);
    }


}
