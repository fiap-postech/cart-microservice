package br.com.fiap.tech.challenge.application.usecase.cart;

import br.com.fiap.tech.challenge.application.gateway.CartReaderGateway;
import br.com.fiap.tech.challenge.application.gateway.CartWriterGateway;
import lombok.AllArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
class CloseCartUseCaseImpl implements CloseCartUseCase {

    private CartReaderGateway cartReaderGateway;
    private CartWriterGateway cartWriterGateway;

    @Override
    public void close(UUID cartUuid) {
        var cart = cartReaderGateway.readById(cartUuid);
        cartWriterGateway.close(cart);
    }
}