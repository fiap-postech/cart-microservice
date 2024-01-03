package br.com.fiap.tech.challenge.application.usecase.cart;

import br.com.fiap.tech.challenge.application.gateway.CartReaderGateway;
import br.com.fiap.tech.challenge.application.gateway.CartWriterGateway;
import br.com.fiap.tech.challenge.application.dto.RemoveCartItemDTO;
import br.com.fiap.tech.challenge.enterprise.entity.Cart;
import lombok.AllArgsConstructor;

import java.util.UUID;

import static java.util.UUID.fromString;

@AllArgsConstructor
class RemoveCartItemUseCaseImpl implements RemoveCartItemUseCase {

    private CartReaderGateway cartReaderGateway;
    private CartWriterGateway writerService;

    @Override
    public Cart remove(UUID uuid, RemoveCartItemDTO dto) {
        var cart = cartReaderGateway.readById(uuid);
        return writerService.write(cart.removeItem(fromString(dto.getProductId())));
    }
}