package br.com.fiap.tech.challenge.application.usecase.cart;

import br.com.fiap.tech.challenge.application.gateway.CartWriterGateway;
import br.com.fiap.tech.challenge.application.usecase.customer.FindCustomerByUUIDUseCase;
import br.com.fiap.tech.challenge.enterprise.entity.Cart;
import br.com.fiap.tech.challenge.exception.ApplicationException;
import org.instancio.Instancio;
import org.instancio.Model;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

import static br.com.fiap.tech.challenge.application.fixture.CartFixture.UUID_CART;
import static br.com.fiap.tech.challenge.application.fixture.CreateCartDTOFixture.buildCreateCartDTOModel;
import static br.com.fiap.tech.challenge.application.fixture.CustomerFixture.createCustomerModel;
import static br.com.fiap.tech.challenge.application.fixture.CustomerFixture.createDisableCustomerModel;
import static br.com.fiap.tech.challenge.application.fixture.Fixture.create;
import static br.com.fiap.tech.challenge.enterprise.error.ApplicationError.CART_CUSTOMER_NOT_AVAILABLE;
import static br.com.fiap.tech.challenge.enterprise.error.ApplicationError.CUSTOMER_NOT_FOUND_BY_UUID;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.instancio.Select.field;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreateCartUseCaseTest {

    @Mock
    private CartWriterGateway writer;

    @Mock
    private FindCustomerByUUIDUseCase findCustomerByUUIDUseCase;

    private CreateCartUseCase useCase;

    @BeforeEach
    void setUp() {
        useCase = CartUseCaseFactory.createCartService(findCustomerByUUIDUseCase, writer);
    }

    @Nested
    class SuccessCases {
        @Test
        void shouldCreateCart() {
            var createCartDTO = create(buildCreateCartDTOModel());
            var customerId = UUID.fromString(createCartDTO.getCustomerId());
            var customer = create(createCustomerModel());
            var expected = create(createCartOnlyCustomer());

            when(findCustomerByUUIDUseCase.get(customerId)).thenReturn(Optional.of(customer));
            when(writer.write(any(Cart.class))).thenReturn(expected);

            var response = useCase.create(createCartDTO);

            assertThat(response).usingRecursiveComparison().isEqualTo(expected);

            verify(findCustomerByUUIDUseCase).get(customerId);
            verify(writer).write(any(Cart.class));
        }
    }

    @Nested
    class FailCases {

        @Test
        void shouldThrowAnErrorWhenNotFoundCustomer() {
            var createCartDTO = create(buildCreateCartDTOModel());
            var customerId = UUID.fromString(createCartDTO.getCustomerId());
            var exception = new ApplicationException(CUSTOMER_NOT_FOUND_BY_UUID, customerId);

            when(findCustomerByUUIDUseCase.get(customerId)).thenReturn(Optional.empty());

            assertThatThrownBy(() -> useCase.create(createCartDTO))
                    .isInstanceOf(ApplicationException.class)
                    .hasMessage(exception.getMessage());

            verify(writer, never()).write(any(Cart.class));
        }

        @Test
        void shouldThrowAnErrorWhenCustomerNotEnabled() {
            var createCartDTO = create(buildCreateCartDTOModel());
            var customerId = UUID.fromString(createCartDTO.getCustomerId());
            var customerDisable = create(createDisableCustomerModel());
            var exception = new ApplicationException(CART_CUSTOMER_NOT_AVAILABLE, customerId);

            when(findCustomerByUUIDUseCase.get(customerId)).thenReturn(Optional.of(customerDisable));

            assertThatThrownBy(() -> useCase.create(createCartDTO))
                    .isInstanceOf(ApplicationException.class)
                    .hasMessage(exception.getMessage());

            verify(findCustomerByUUIDUseCase).get(customerId);
            verify(writer, never()).write(any(Cart.class));
        }
    }

    private Model<Cart> createCartOnlyCustomer(){
        return Instancio.of(Cart.class)
                .set(field(Cart::customer), create(createCustomerModel()))
                .set(field(Cart::items), Collections.emptyList())
                .set(field(Cart::uuid), UUID_CART)
                .toModel();
    }
}
