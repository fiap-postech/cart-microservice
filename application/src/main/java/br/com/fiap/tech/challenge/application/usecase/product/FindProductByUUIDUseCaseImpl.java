package br.com.fiap.tech.challenge.application.usecase.product;

import br.com.fiap.tech.challenge.application.gateway.ProductReaderGateway;
import br.com.fiap.tech.challenge.enterprise.entity.Product;
import br.com.fiap.tech.challenge.exception.ApplicationException;
import lombok.AllArgsConstructor;

import java.util.UUID;

import static br.com.fiap.tech.challenge.enterprise.error.ApplicationError.PRODUCT_NOT_FOUND_BY_UUID;

@AllArgsConstructor
class FindProductByUUIDUseCaseImpl implements FindProductByUUIDUseCase {

    private ProductReaderGateway readerGateway;

    @Override
    public Product get(UUID uuid) {
        return readerGateway.readById(uuid)
                .orElseThrow(() -> new ApplicationException(PRODUCT_NOT_FOUND_BY_UUID, uuid.toString()));
    }
}
