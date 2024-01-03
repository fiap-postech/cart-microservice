package br.com.fiap.tech.challenge.adapter.controller.cart;

import br.com.fiap.tech.challenge.adapter.presenter.CartPresenter;
import br.com.fiap.tech.challenge.application.usecase.cart.RemoveCartItemUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static br.com.fiap.tech.challenge.adapter.fixture.CartDTOFixture.createCartDTOModel;
import static br.com.fiap.tech.challenge.adapter.fixture.CartFixture.UUID_CART;
import static br.com.fiap.tech.challenge.adapter.fixture.CartFixture.createCartModel;
import static br.com.fiap.tech.challenge.adapter.fixture.Fixture.create;
import static br.com.fiap.tech.challenge.adapter.fixture.RemoveCartItemDTOFixture.createRemoveCartItemDTOModel;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RemoveCartItemControllerTest {

    @Mock
    private RemoveCartItemUseCase useCase;

    @Mock
    private CartPresenter presenter;

    private RemoveCartItemController controller;

    @BeforeEach
    void setUp(){
        controller = CartControllerFactory.removeCartItemController(useCase, presenter);
    }

    @Test
    void shouldRemoveCartItemInCart() {
        var removeCartItemDTO = create(createRemoveCartItemDTOModel());
        var cart = create(createCartModel());
        var cartDTO = create(createCartDTOModel());

        when(useCase.remove(UUID_CART, removeCartItemDTO)).thenReturn(cart);
        when(presenter.present(cart)).thenReturn(cartDTO);

        var response = controller.remove(UUID_CART.toString(), removeCartItemDTO);

        assertThat(response)
                .isNotNull()
                .isEqualTo(cartDTO);

        verify(useCase).remove(UUID_CART, removeCartItemDTO);
        verify(presenter).present(cart);
    }
}
