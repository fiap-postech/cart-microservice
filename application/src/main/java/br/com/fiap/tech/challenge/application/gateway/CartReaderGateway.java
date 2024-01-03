package br.com.fiap.tech.challenge.application.gateway;

import br.com.fiap.tech.challenge.enterprise.entity.Cart;

import java.util.UUID;

public interface CartReaderGateway {

    Cart readById(UUID id);

}