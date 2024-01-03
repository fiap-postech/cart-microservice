package br.com.fiap.tech.challenge.adapter.gateway.product;

import br.com.fiap.tech.challenge.adapter.mapping.util.ProductMappers;
import br.com.fiap.tech.challenge.adapter.repository.ProductReaderRepository;
import br.com.fiap.tech.challenge.application.gateway.ProductReaderGateway;
import br.com.fiap.tech.challenge.enterprise.entity.Product;
import lombok.RequiredArgsConstructor;

import java.util.Optional;
import java.util.UUID;

import static br.com.fiap.tech.challenge.adapter.mapping.util.ProductMappers.toProductDomain;

@RequiredArgsConstructor
class ProductReaderGatewayImpl implements ProductReaderGateway {

    private final ProductReaderRepository repository;

    @Override
    public Optional<Product> readById(UUID id) {
        return repository.readById(id.toString())
                .map(ProductMappers::toProductDomain);
    }
}
