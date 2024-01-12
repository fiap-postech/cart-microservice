package br.com.fiap.tech.challenge.customer.launcher.containers;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.mockserver.client.MockServerClient;
import org.testcontainers.containers.MockServerContainer;
import org.testcontainers.utility.DockerImageName;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MockServerContainers {

    private static final DockerImageName MOCK_SERVER_IMAGE = DockerImageName
            .parse("mockserver/mockserver")
            .withTag("mockserver-" + MockServerClient.class.getPackage().getImplementationVersion());

    public static MockServerContainer createMockServerContainer() {
        return new MockServerContainer(MOCK_SERVER_IMAGE);
    }
}
