package br.com.fiap.tech.challenge.adapter.gateway.cart;

import br.com.fiap.tech.challenge.adapter.mapping.CartMapper;
import br.com.fiap.tech.challenge.adapter.repository.CartWriterRepository;
import br.com.fiap.tech.challenge.adapter.repository.CloseCartRepository;
import br.com.fiap.tech.challenge.application.gateway.CartWriterGateway;
import br.com.fiap.tech.challenge.enterprise.entity.Cart;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
class CartWriterGatewayImpl implements CartWriterGateway {

    private final CartWriterRepository cartWriterRepository;
    private final CloseCartRepository closeCartRepository;

    @Override
    public Cart write(Cart cart) {
        var mapper = CartMapper.INSTANCE;
        return mapper.toDomain(cartWriterRepository.write(mapper.toDTO(cart)));
    }

    @Override
    public void close(Cart cart) {
        var mapper = CartMapper.INSTANCE;
        closeCartRepository.close(mapper.toDTO(cart));
    }

    @Override
    public void deleteById(UUID id) {
        cartWriterRepository.deleteById(id.toString());
    }
}
