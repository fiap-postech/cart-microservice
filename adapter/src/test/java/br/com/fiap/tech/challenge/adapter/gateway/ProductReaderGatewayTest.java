package br.com.fiap.tech.challenge.adapter.gateway;

import br.com.fiap.tech.challenge.adapter.gateway.product.ProductGatewayFactory;
import br.com.fiap.tech.challenge.adapter.repository.ProductReaderRepository;
import br.com.fiap.tech.challenge.application.gateway.ProductReaderGateway;
import br.com.fiap.tech.challenge.exception.ApplicationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static br.com.fiap.tech.challenge.adapter.fixture.Fixture.create;
import static br.com.fiap.tech.challenge.adapter.fixture.ProductDTOFixture.createSandwichDTOModel;
import static br.com.fiap.tech.challenge.adapter.fixture.ProductFixture.createSandwichModel;
import static br.com.fiap.tech.challenge.enterprise.error.ApplicationError.PRODUCT_NOT_FOUND_BY_UUID;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductReaderGatewayTest {

    @Mock
    private ProductReaderRepository repository;

    private ProductReaderGateway gateway;

    @BeforeEach
    void setUp() {
        gateway = ProductGatewayFactory.productReaderGateway(repository);
    }

    @Nested
    class FindProductByUUID {
        @Test
        void shouldReturnProductWhenExists() {
            var productDTO = create(createSandwichDTOModel());
            var product = create(createSandwichModel());
            var uuid = product.uuid();

            when(repository.readById(uuid.toString())).thenReturn(Optional.of(productDTO));

            var response = gateway.readById(uuid);

            assertThat(response.isPresent()).isTrue();

            assertThat(response.get())
                    .isNotNull()
                    .usingRecursiveComparison()
                    .isEqualTo(product);

            verify(repository).readById(uuid.toString());
        }

        @Test
        void shouldThrowExceptionWhenProductDoesNotExists() {
            var uuid = UUID.randomUUID();
            var uuidText = uuid.toString();
            var exception = new ApplicationException(PRODUCT_NOT_FOUND_BY_UUID, uuidText);

            when(repository.readById(uuid.toString())).thenThrow(exception);

            assertThatThrownBy(() -> gateway.readById(uuid))
                    .isInstanceOf(ApplicationException.class)
                    .hasMessage(exception.getMessage());

            verify(repository).readById(uuid.toString());
        }
    }
}
