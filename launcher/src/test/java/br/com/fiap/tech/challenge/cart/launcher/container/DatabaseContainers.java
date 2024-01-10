package br.com.fiap.tech.challenge.cart.launcher.container;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.containers.Network;
import org.testcontainers.utility.DockerImageName;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DatabaseContainers {

    public static GenericContainer<?> localRedisContainer(){
        return new GenericContainer<>(DockerImageName.parse("library/redis:6-alpine"))
                .withExposedPorts(6379);
    }

    public static MySQLContainer<?> centralDatabaseContainer(Network network){
        return new MySQLContainer<>(DockerImageName.parse("mysql:8.0"))
                .withNetwork(network)
                .withCommand("--default-authentication-plugin=mysql_native_password")
                .withUsername("root")
                .withPassword("root")
                .withInitScript("db/scripts/setup-database.sql");
    }

}
