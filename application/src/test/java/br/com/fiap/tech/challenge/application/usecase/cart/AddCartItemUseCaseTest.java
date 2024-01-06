package br.com.fiap.tech.challenge.application.usecase.cart;

import br.com.fiap.tech.challenge.application.gateway.CartReaderGateway;
import br.com.fiap.tech.challenge.application.gateway.CartWriterGateway;
import br.com.fiap.tech.challenge.application.usecase.product.FindProductByUUIDUseCase;
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
import java.util.UUID;

import static br.com.fiap.tech.challenge.application.fixture.AddCartItemDTOFixture.createAddCartItemDTOModel;
import static br.com.fiap.tech.challenge.application.fixture.CartFixture.UUID_CART;
import static br.com.fiap.tech.challenge.application.fixture.CartFixture.createCartModel;
import static br.com.fiap.tech.challenge.application.fixture.CustomerFixture.createCustomerModel;
import static br.com.fiap.tech.challenge.application.fixture.Fixture.create;
import static br.com.fiap.tech.challenge.application.fixture.ProductFixture.createSandwichModel;
import static br.com.fiap.tech.challenge.enterprise.error.ApplicationError.CART_NOT_FOUND_BY_UUID;
import static br.com.fiap.tech.challenge.enterprise.error.ApplicationError.PRODUCT_NOT_FOUND_BY_UUID;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.instancio.Select.field;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AddCartItemUseCaseTest {

    @Mock
    private CartReaderGateway reader;

    @Mock
    private CartWriterGateway writer;

    @Mock
    private FindProductByUUIDUseCase findProductByUUIDUseCase;

    private AddCartItemUseCase useCase;

    @BeforeEach
    void setUp() {
        useCase = CartUseCaseFactory.addCartItemService(reader, writer, findProductByUUIDUseCase);
    }

    @Nested
    class SuccessCases {
        @Test
        void shouldAddCartItemInCart() {
            var addCartItemDTO = create(createAddCartItemDTOModel());
            var cart = create(createCartWithoutItems());
            var expected = create(createCartModel());
            var product = create(createSandwichModel());
            var productId = UUID.fromString(addCartItemDTO.getProductId());

            when(reader.readById(UUID_CART)).thenReturn(cart);
            when(writer.write(any(Cart.class))).thenReturn(expected);
            when(findProductByUUIDUseCase.get(productId)).thenReturn(product);

            var response = useCase.add(UUID_CART, addCartItemDTO);

            assertThat(response).usingRecursiveComparison().isEqualTo(expected);

            verify(reader).readById(UUID_CART);
            verify(writer).write(any(Cart.class));
            verify(findProductByUUIDUseCase).get(productId);
        }
    }

    @Nested
    class FailCases {

        @Test
        void shouldThrowAnErrorWhenNotFoundCart() {
            var addCartItemDTO = create(createAddCartItemDTOModel());
            var productId = UUID.fromString(addCartItemDTO.getProductId());
            var exception = new ApplicationException(CART_NOT_FOUND_BY_UUID, UUID_CART);

            when(reader.readById(UUID_CART)).thenThrow(exception);

            assertThatThrownBy(() -> useCase.add(UUID_CART, addCartItemDTO))
                    .isInstanceOf(ApplicationException.class)
                    .hasMessage(exception.getMessage());

            verify(reader).readById(UUID_CART);
            verify(writer, never()).write(any(Cart.class));
            verify(findProductByUUIDUseCase, never()).get(productId);
        }

        @Test
        void shouldThrowAnErrorWhenNotFoundProduct() {
            var addCartItemDTO = create(createAddCartItemDTOModel());
            var cart = create(createCartWithoutItems());
            var productId = UUID.fromString(addCartItemDTO.getProductId());
            var exception = new ApplicationException(PRODUCT_NOT_FOUND_BY_UUID, productId);

            when(reader.readById(UUID_CART)).thenReturn(cart);
            when(findProductByUUIDUseCase.get(productId)).thenThrow(exception);

            assertThatThrownBy(() -> useCase.add(UUID_CART, addCartItemDTO))
                    .isInstanceOf(ApplicationException.class)
                    .hasMessage(exception.getMessage());

            verify(reader).readById(UUID_CART);
            verify(findProductByUUIDUseCase).get(productId);
            verify(writer, never()).write(any(Cart.class));
        }
    }

    private Model<Cart> createCartWithoutItems(){
        return Instancio.of(Cart.class)
                .set(field(Cart::customer), create(createCustomerModel()))
                .set(field(Cart::items), Collections.emptyList())
                .set(field(Cart::uuid), UUID_CART)
                .toModel();
    }
}
