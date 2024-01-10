package br.com.fiap.tech.challenge.cart.launcher.rest;

import br.com.fiap.tech.challenge.cart.launcher.config.TestConfiguration;
import br.com.fiap.tech.challenge.cart.launcher.container.MicroserviceContainers;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.containers.Network;
import org.testcontainers.containers.output.Slf4jLogConsumer;

import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

import static br.com.fiap.tech.challenge.cart.launcher.container.DatabaseContainers.customerDatabaseContainer;
import static br.com.fiap.tech.challenge.cart.launcher.container.DatabaseContainers.productDatabaseContainer;
import static br.com.fiap.tech.challenge.cart.launcher.container.MicroserviceContainers.customerServiceContainer;
import static br.com.fiap.tech.challenge.cart.launcher.container.MicroserviceContainers.productServiceContainer;
import static org.assertj.core.api.Assertions.assertThat;
import static org.slf4j.LoggerFactory.getLogger;
import static org.springframework.test.annotation.DirtiesContext.ClassMode.AFTER_CLASS;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT,
        classes = TestConfiguration.class
)
@ActiveProfiles({"test"})
@DirtiesContext(classMode = AFTER_CLASS)
class CreateCartResourceIT {

    private static final Logger LOGGER = getLogger(CreateCartResourceIT.class);

    private static final int LOCAL_PORT = 8689;

    private static final Network NETWORK = Network.newNetwork();

    private static MySQLContainer<?> customerDatabaseContainer;

    private static MySQLContainer<?> productDatabaseContainer;

    private static GenericContainer<?> customerServiceContainer;

    private static GenericContainer<?> productServiceContainer;

    @BeforeAll
    static void startContainers() {
        customerDatabaseContainer = customerDatabaseContainer(NETWORK);
        customerDatabaseContainer.start();

        productDatabaseContainer = productDatabaseContainer(NETWORK);
        productDatabaseContainer.start();

        customerServiceContainer = customerServiceContainer(NETWORK, customerDatabaseContainer);
        customerServiceContainer.setPortBindings(List.of("8689:8689"));
        customerServiceContainer.start();
        customerServiceContainer.followOutput(new Slf4jLogConsumer(LOGGER));

        productServiceContainer = productServiceContainer(NETWORK, productDatabaseContainer);
        productServiceContainer.setPortBindings(List.of("8688:8688"));
        productServiceContainer.start();
        productServiceContainer.followOutput(new Slf4jLogConsumer(LOGGER));

        System.setProperty("customer.host", "http://localhost:8689");
        System.setProperty("product.host", "http://localhost:8688");
    }

    @AfterAll
    static void stopContainers() {
        Stream.of(productDatabaseContainer, customerDatabaseContainer, productServiceContainer, customerServiceContainer)
                .filter(Objects::nonNull)
                .forEach(GenericContainer::stop);
    }


    @Test
    void testUp() {
        assertThat(customerDatabaseContainer.isRunning()).isTrue();
        assertThat(customerServiceContainer.isRunning()).isTrue();
        assertThat(productDatabaseContainer.isRunning()).isTrue();
        assertThat(productServiceContainer.isRunning()).isTrue();

    }


}
