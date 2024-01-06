package br.com.fiap.tech.challenge.adapter.controller.cart;

import br.com.fiap.tech.challenge.adapter.presenter.CartPresenter;
import br.com.fiap.tech.challenge.application.usecase.cart.AddCartItemUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static br.com.fiap.tech.challenge.adapter.fixture.AddCartItemDTOFixture.createAddCartItemDTOModel;
import static br.com.fiap.tech.challenge.adapter.fixture.CartDTOFixture.createCartDTOModel;
import static br.com.fiap.tech.challenge.adapter.fixture.CartFixture.createCartModel;
import static br.com.fiap.tech.challenge.adapter.fixture.CustomerFixture.UUID_CUSTOMER;
import static br.com.fiap.tech.challenge.adapter.fixture.Fixture.create;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AddCartItemControllerTest {

    @Mock
    private AddCartItemUseCase useCase;

    @Mock
    private CartPresenter presenter;

    private AddCartItemController controller;

    @BeforeEach
    void setUp(){
        controller = CartControllerFactory.addCartItemController(useCase, presenter);
    }

    @Test
    void shouldAddCartItemInCart() {
        var createAddCartItemDTO = create(createAddCartItemDTOModel());
        var cart = create(createCartModel());
        var cartDTO = create(createCartDTOModel());

        when(useCase.add(UUID_CUSTOMER, createAddCartItemDTO)).thenReturn(cart);
        when(presenter.present(cart)).thenReturn(cartDTO);

        var response = controller.add(UUID_CUSTOMER.toString(), createAddCartItemDTO);

        assertThat(response)
                .isNotNull()
                .isEqualTo(cartDTO);

        verify(useCase).add(UUID_CUSTOMER, createAddCartItemDTO);
        verify(presenter).present(cart);
    }
}
