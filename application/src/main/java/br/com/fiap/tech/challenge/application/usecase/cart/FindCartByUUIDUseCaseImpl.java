package br.com.fiap.tech.challenge.application.usecase.cart;

import br.com.fiap.tech.challenge.application.gateway.CartReaderGateway;
import br.com.fiap.tech.challenge.enterprise.entity.Cart;
import lombok.AllArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
class FindCartByUUIDUseCaseImpl implements FindCartByUUIDUseCase {

    private CartReaderGateway readerService;

    @Override
    public Cart get(UUID uuid) {
        return readerService.readById(uuid);
    }
}