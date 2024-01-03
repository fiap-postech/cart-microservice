package br.com.fiap.tech.challenge.adapter.controller.cart;

import br.com.fiap.tech.challenge.adapter.presenter.CartPresenter;
import br.com.fiap.tech.challenge.application.usecase.cart.UpdateCartItemUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static br.com.fiap.tech.challenge.adapter.fixture.CartDTOFixture.createCartDTOModel;
import static br.com.fiap.tech.challenge.adapter.fixture.CartFixture.UUID_CART;
import static br.com.fiap.tech.challenge.adapter.fixture.CartFixture.createCartModel;
import static br.com.fiap.tech.challenge.adapter.fixture.Fixture.create;
import static br.com.fiap.tech.challenge.adapter.fixture.UpdateCartItemDTOFixture.createUpdateCartItemDTOModel;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UpdateCartItemControllerTest {

    @Mock
    private UpdateCartItemUseCase useCase;

    @Mock
    private CartPresenter presenter;

    private UpdateCartItemController controller;

    @BeforeEach
    void setUp(){
        controller = CartControllerFactory.updateCartItemController(useCase, presenter);
    }

    @Test
    void shouldUpdateCartItemInCart() {
        var updateCartItemDTO = create(createUpdateCartItemDTOModel());
        var cart = create(createCartModel());
        var cartDTO = create(createCartDTOModel());

        when(useCase.update(UUID_CART, updateCartItemDTO)).thenReturn(cart);
        when(presenter.present(cart)).thenReturn(cartDTO);

        var response = controller.update(UUID_CART.toString(), updateCartItemDTO);

        assertThat(response)
                .isNotNull()
                .isEqualTo(cartDTO);

        verify(useCase).update(UUID_CART, updateCartItemDTO);
        verify(presenter).present(cart);
    }
}
