package br.com.fiap.tech.challenge.adapter.controller.cart;

import br.com.fiap.tech.challenge.adapter.presenter.CartPresenter;
import br.com.fiap.tech.challenge.application.usecase.cart.AddCartItemUseCase;
import br.com.fiap.tech.challenge.application.usecase.cart.CreateCartUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static br.com.fiap.tech.challenge.adapter.fixture.AddCartItemDTOFixture.createAddCartItemDTOModel;
import static br.com.fiap.tech.challenge.adapter.fixture.CartDTOFixture.createCartDTOModel;
import static br.com.fiap.tech.challenge.adapter.fixture.CartFixture.createCartModel;
import static br.com.fiap.tech.challenge.adapter.fixture.CreateCartDTOFixture.buildCreateCartDTOModel;
import static br.com.fiap.tech.challenge.adapter.fixture.CustomerFixture.UUID_CUSTOMER;
import static br.com.fiap.tech.challenge.adapter.fixture.Fixture.create;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreateCartControllerTest {

    @Mock
    private CreateCartUseCase useCase;

    @Mock
    private CartPresenter presenter;

    private CreateCartController controller;

    @BeforeEach
    void setUp(){
        controller = CartControllerFactory.createCartController(useCase, presenter);
    }

    @Test
    void shouldCreateCart() {
        var createCartDTO = create(buildCreateCartDTOModel());
        var cart = create(createCartModel());
        var cartDTO = create(createCartDTOModel());

        when(useCase.create(createCartDTO)).thenReturn(cart);
        when(presenter.present(cart)).thenReturn(cartDTO);

        var response = controller.create(createCartDTO);

        assertThat(response)
                .isNotNull()
                .isEqualTo(cartDTO);

        verify(useCase).create(createCartDTO);
        verify(presenter).present(cart);
    }
}
