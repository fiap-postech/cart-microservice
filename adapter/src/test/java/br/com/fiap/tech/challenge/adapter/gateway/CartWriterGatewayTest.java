package br.com.fiap.tech.challenge.adapter.gateway;

import br.com.fiap.tech.challenge.adapter.dto.CartDTO;
import br.com.fiap.tech.challenge.adapter.gateway.cart.CartGatewayFactory;
import br.com.fiap.tech.challenge.adapter.repository.CartWriterRepository;
import br.com.fiap.tech.challenge.adapter.repository.CloseCartRepository;
import br.com.fiap.tech.challenge.application.gateway.CartWriterGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static br.com.fiap.tech.challenge.adapter.fixture.CartDTOFixture.createCartDTOModel;
import static br.com.fiap.tech.challenge.adapter.fixture.CartFixture.createCartModel;
import static br.com.fiap.tech.challenge.adapter.fixture.Fixture.create;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CartWriterGatewayTest {

    @Mock
    private CartWriterRepository cartWriterRepository;

    @Mock
    private CloseCartRepository closeCartRepository;

    @Captor
    private ArgumentCaptor<CartDTO> cartDTOArgumentCaptor;

    private CartWriterGateway gateway;

    @BeforeEach
    void setUp() {
        gateway = CartGatewayFactory.cartWriterGateway(cartWriterRepository, closeCartRepository);
    }

    @Test
    void shouldWriteCart() {
        var cartDTO = create(createCartDTOModel());
        var cart = create(createCartModel());

        when(cartWriterRepository.write(any(CartDTO.class))).thenReturn(cartDTO);

        var response = gateway.write(cart);

        assertThat(response)
                .isNotNull()
                .usingRecursiveComparison()
                .isEqualTo(cart);

        verify(cartWriterRepository).write(any(CartDTO.class));
    }

    @Test
    void shouldCloseCart() {
        var cartDTO = create(createCartDTOModel());
        var cart = create(createCartModel());

        doNothing().when(closeCartRepository).close(any(CartDTO.class));

        gateway.close(cart);

        verify(closeCartRepository).close(cartDTOArgumentCaptor.capture());

        var cartDTOResult = cartDTOArgumentCaptor.getValue();

        assertThat(cartDTOResult)
                .isNotNull()
                .usingRecursiveComparison()
                .isEqualTo(cartDTO);
    }
}
