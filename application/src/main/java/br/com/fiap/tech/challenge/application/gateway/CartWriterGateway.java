package br.com.fiap.tech.challenge.application.gateway;

import br.com.fiap.tech.challenge.enterprise.entity.Cart;

import java.util.UUID;

public interface CartWriterGateway {

    Cart write(Cart cart);

    void close(Cart cart);

    void deleteById(UUID id);
}