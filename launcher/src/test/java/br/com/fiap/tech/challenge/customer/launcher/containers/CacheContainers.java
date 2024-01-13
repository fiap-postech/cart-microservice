package br.com.fiap.tech.challenge.customer.launcher.containers;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.utility.DockerImageName;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CacheContainers {

    public static GenericContainer<?> localRedisContainer(){
        var container = new GenericContainer<>(DockerImageName.parse("library/redis:6-alpine"))
                .withExposedPorts(6379);

        container.setPortBindings(List.of("6380:6379"));

        return container;
    }

}
