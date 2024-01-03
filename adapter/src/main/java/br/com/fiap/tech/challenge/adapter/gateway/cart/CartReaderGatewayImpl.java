package br.com.fiap.tech.challenge.adapter.gateway.cart;

import br.com.fiap.tech.challenge.adapter.mapping.CartMapper;
import br.com.fiap.tech.challenge.adapter.repository.CartReaderRepository;
import br.com.fiap.tech.challenge.enterprise.entity.Cart;
import br.com.fiap.tech.challenge.application.gateway.CartReaderGateway;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
class CartReaderGatewayImpl implements CartReaderGateway {

    private final CartReaderRepository repository;

    @Override
    public Cart readById(UUID id) {
        return CartMapper.INSTANCE.toDomain(repository.readById(id.toString()));
    }
}
