package br.com.fiap.tech.challenge.adapter.controller.cart;

import br.com.fiap.tech.challenge.adapter.presenter.CartPresenter;
import br.com.fiap.tech.challenge.application.usecase.cart.CreateCartUseCase;
import br.com.fiap.tech.challenge.application.usecase.cart.FindCartByUUIDUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static br.com.fiap.tech.challenge.adapter.fixture.CartDTOFixture.createCartDTOModel;
import static br.com.fiap.tech.challenge.adapter.fixture.CartFixture.UUID_CART;
import static br.com.fiap.tech.challenge.adapter.fixture.CartFixture.createCartModel;
import static br.com.fiap.tech.challenge.adapter.fixture.CreateCartDTOFixture.buildCreateCartDTOModel;
import static br.com.fiap.tech.challenge.adapter.fixture.Fixture.create;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FindCartByUUIDControllerTest {

    @Mock
    private FindCartByUUIDUseCase useCase;

    @Mock
    private CartPresenter presenter;

    private FindCartByUUIDController controller;

    @BeforeEach
    void setUp(){
        controller = CartControllerFactory.findCartByUUIDController(useCase, presenter);
    }

    @Test
    void shouldFindCartByUUID() {
        var cart = create(createCartModel());
        var cartDTO = create(createCartDTOModel());

        when(useCase.get(UUID_CART)).thenReturn(cart);
        when(presenter.present(cart)).thenReturn(cartDTO);

        var response = controller.get(UUID_CART.toString());

        assertThat(response)
                .isNotNull()
                .isEqualTo(cartDTO);

        verify(useCase).get(UUID_CART);
        verify(presenter).present(cart);
    }
}
