package br.com.fiap.tech.challenge.application.gateway;

import br.com.fiap.tech.challenge.enterprise.entity.Cart;

public interface CartWriterGateway {

    Cart write(Cart cart);

    Cart close(Cart cart);
}