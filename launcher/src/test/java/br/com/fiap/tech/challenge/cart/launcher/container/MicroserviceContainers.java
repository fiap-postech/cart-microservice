package br.com.fiap.tech.challenge.cart.launcher.container;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.containers.Network;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.utility.DockerImageName;

import java.time.Duration;

import static br.com.fiap.tech.challenge.cart.launcher.util.Databases.replaceDockerHost;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MicroserviceContainers {

    public static GenericContainer<?> customerServiceContainer(Network network, MySQLContainer<?> database){
        return new GenericContainer<>(DockerImageName.parse("fiapsoat2grupo13/customer-service:latest"))
                .withEnv("spring.profiles.active", "local")
                .withEnv("spring.datasource.url", replaceDockerHost(database.getJdbcUrl()))
                .withEnv("spring.datasource.username", database.getUsername())
                .withEnv("spring.datasource.password", database.getPassword())
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
                .withEnv("spring.datasource.url", replaceDockerHost(database.getJdbcUrl()))
                .withEnv("spring.datasource.username", database.getUsername())
                .withEnv("spring.datasource.password", database.getPassword())
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
