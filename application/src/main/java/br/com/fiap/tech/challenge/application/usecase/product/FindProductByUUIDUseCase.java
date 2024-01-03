package br.com.fiap.tech.challenge.application.usecase.product;

import br.com.fiap.tech.challenge.enterprise.entity.Product;

import java.util.UUID;

public interface FindProductByUUIDUseCase {

    Product get(UUID uuid);

}
