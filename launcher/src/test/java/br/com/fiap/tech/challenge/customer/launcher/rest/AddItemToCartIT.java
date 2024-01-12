package br.com.fiap.tech.challenge.customer.launcher.rest;

import br.com.fiap.tech.challenge.customer.launcher.Expectations.GetProductExpectations;
import br.com.fiap.tech.challenge.customer.launcher.config.TestConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockserver.client.MockServerClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.MockServerContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static br.com.fiap.tech.challenge.customer.launcher.Expectations.GetCustomerExpectations.getCustomerSuccessExpectation;
import static br.com.fiap.tech.challenge.customer.launcher.containers.CacheContainers.localRedisContainer;
import static br.com.fiap.tech.challenge.customer.launcher.containers.MockServerContainers.createMockServerContainer;
import static br.com.fiap.tech.challenge.customer.launcher.fixture.AddCartItemRequestFixture.addOneBeverageModel;
import static br.com.fiap.tech.challenge.customer.launcher.fixture.CreateCartRequestFixture.createCartRequestModel;
import static br.com.fiap.tech.challenge.customer.launcher.fixture.CustomerResponseFixture.consumerCustomerResponseModel;
import static br.com.fiap.tech.challenge.customer.launcher.fixture.Fixture.create;
import static br.com.fiap.tech.challenge.customer.launcher.fixture.ProductResponseFixture.beverageModel;
import static br.com.fiap.tech.challenge.customer.launcher.util.CartUtil.createCart;
import static br.com.fiap.tech.challenge.customer.launcher.util.ConfigurationOverrides.overrideConfiguration;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static java.util.Objects.isNull;
import static org.mockserver.verify.VerificationTimes.exactly;
import static org.springframework.test.annotation.DirtiesContext.ClassMode.AFTER_CLASS;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT,
        classes = TestConfiguration.class
)
@ActiveProfiles({"test"})
@DirtiesContext(classMode = AFTER_CLASS)
@Testcontainers
class AddItemToCartIT {

    @Container
    protected static MockServerContainer MOCK_SERVER_CONTAINER = createMockServerContainer();

    @Container
    protected static GenericContainer<?> REDIS_CONTAINER = localRedisContainer();

    protected MockServerClient mockServerClient;

    @DynamicPropertySource
    static void overrideConfig(DynamicPropertyRegistry registry) {
        overrideConfiguration(registry, MOCK_SERVER_CONTAINER.getEndpoint(), REDIS_CONTAINER.getHost());
    }

    @BeforeEach
    void init () {
        if (isNull(mockServerClient)) {
            mockServerClient = new MockServerClient(
                    MOCK_SERVER_CONTAINER.getHost(),
                    MOCK_SERVER_CONTAINER.getServerPort()
            );
        }

        mockServerClient.reset();
    }

    @Test
    void testAddOneBeverageToCart() {
        var createCartRequest = create(createCartRequestModel());
        var addItemRequest = create(addOneBeverageModel());

        var mockCustomerRequest = getCustomerSuccessExpectation(
                mockServerClient,
                createCartRequest.getCustomerId(),
                consumerCustomerResponseModel()
        );

        var mockProductRequest = GetProductExpectations.getProductSuccessExpectation(
                mockServerClient,
                addItemRequest.getProductId(),
                beverageModel()
        );

        var cartResponse = createCart(createCartRequest);

        given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(addItemRequest)
            .when()
                .post("/cart/{id}/items", cartResponse.getId())
            .then()
                .statusCode(HttpStatus.OK.value())
                .body(matchesJsonSchemaInClasspath("./schemas/CartResponseSchema.json"));

        mockServerClient.verify(mockCustomerRequest, exactly(1));
        mockServerClient.verify(mockProductRequest, exactly(1));
    }
}
