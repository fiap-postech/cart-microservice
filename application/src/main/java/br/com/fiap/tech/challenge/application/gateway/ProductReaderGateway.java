package br.com.fiap.tech.challenge.application.gateway;

import br.com.fiap.tech.challenge.enterprise.entity.Product;

import java.util.Optional;
import java.util.UUID;

public interface ProductReaderGateway {

    Optional<Product> readById(UUID id);

}
