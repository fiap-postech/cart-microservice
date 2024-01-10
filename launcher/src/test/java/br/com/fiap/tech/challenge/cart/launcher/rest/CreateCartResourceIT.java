package br.com.fiap.tech.challenge.cart.launcher.rest;

import br.com.fiap.tech.challenge.cart.launcher.config.TestConfiguration;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.containers.Network;

import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

import static br.com.fiap.tech.challenge.cart.launcher.container.DatabaseContainers.centralDatabaseContainer;
import static br.com.fiap.tech.challenge.cart.launcher.container.MicroserviceContainers.customerServiceContainer;
import static br.com.fiap.tech.challenge.cart.launcher.container.MicroserviceContainers.productServiceContainer;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.annotation.DirtiesContext.ClassMode.AFTER_CLASS;
import static org.testcontainers.lifecycle.Startables.deepStart;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT,
        classes = TestConfiguration.class
)
@ActiveProfiles({"test"})
@DirtiesContext(classMode = AFTER_CLASS)
class CreateCartResourceIT {

    private static final int LOCAL_PORT = 8689;

    private static final Network NETWORK = Network.newNetwork();

    private static GenericContainer<?> customerServiceContainer;

    private static GenericContainer<?> productServiceContainer;

    private static MySQLContainer<?> centralDatabaseContainer;

    @BeforeAll
    static void startContainers() {
        centralDatabaseContainer = centralDatabaseContainer(NETWORK);
        centralDatabaseContainer.setPortBindings(List.of("33996:3306"));
        centralDatabaseContainer.start();

        customerServiceContainer = customerServiceContainer(NETWORK, centralDatabaseContainer);
        customerServiceContainer.setPortBindings(List.of("8689:8689"));

        productServiceContainer = productServiceContainer(NETWORK, centralDatabaseContainer);
        productServiceContainer.setPortBindings(List.of("8688:8688"));

        deepStart(customerServiceContainer, productServiceContainer).join();

        System.setProperty("customer.host", "http://localhost:8689");
        System.setProperty("product.host", "http://localhost:8688");
    }

    @AfterAll
    static void stopContainers() {
        Stream.of(centralDatabaseContainer, productServiceContainer, customerServiceContainer)
                .filter(Objects::nonNull)
                .forEach(GenericContainer::stop);
    }


    @Test
    void testUp() {
        assertThat(centralDatabaseContainer.isRunning()).isTrue();
        assertThat(customerServiceContainer.isRunning()).isTrue();
        assertThat(productServiceContainer.isRunning()).isTrue();
    }


}
