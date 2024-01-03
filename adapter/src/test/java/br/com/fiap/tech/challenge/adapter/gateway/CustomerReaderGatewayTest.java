package br.com.fiap.tech.challenge.adapter.gateway;

import br.com.fiap.tech.challenge.adapter.gateway.customer.CustomerGatewayFactory;
import br.com.fiap.tech.challenge.adapter.repository.CustomerReaderRepository;
import br.com.fiap.tech.challenge.application.gateway.CustomerReaderGateway;
import br.com.fiap.tech.challenge.exception.ApplicationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static br.com.fiap.tech.challenge.adapter.fixture.CustomerDTOFixture.enabledCustomerDTOModel;
import static br.com.fiap.tech.challenge.adapter.fixture.CustomerFixture.createCustomerModel;
import static br.com.fiap.tech.challenge.adapter.fixture.Fixture.create;
import static br.com.fiap.tech.challenge.enterprise.error.ApplicationError.CUSTOMER_NOT_FOUND_BY_UUID;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomerReaderGatewayTest {

    @Mock
    private CustomerReaderRepository repository;

    private CustomerReaderGateway gateway;

    @BeforeEach
    void setUp() {
        gateway = CustomerGatewayFactory.customerReaderGateway(repository);
    }

    @Nested
    class FindCustomerByUUID {
        @Test
        void shouldReturnCustomerWhenExists() {
            var customerDTO = create(enabledCustomerDTOModel());
            var customer = create(createCustomerModel());
            var uuid = customer.uuid();

            when(repository.readByID(uuid.toString())).thenReturn(Optional.of(customerDTO));

            var response = gateway.readByID(uuid);

            assertThat(response.isPresent()).isTrue();

            assertThat(response.get())
                    .isNotNull()
                    .usingRecursiveComparison()
                    .isEqualTo(customer);

            verify(repository).readByID(uuid.toString());
        }

        @Test
        void shouldThrowExceptionWhenCustomerDoesNotExists() {
            var uuid = UUID.randomUUID();
            var uuidText = uuid.toString();
            var exception = new ApplicationException(CUSTOMER_NOT_FOUND_BY_UUID, uuidText);

            when(repository.readByID(uuid.toString())).thenThrow(exception);

            assertThatThrownBy(() -> gateway.readByID(uuid))
                    .isInstanceOf(ApplicationException.class)
                    .hasMessage(exception.getMessage());

            verify(repository).readByID(uuid.toString());
        }
    }
}
