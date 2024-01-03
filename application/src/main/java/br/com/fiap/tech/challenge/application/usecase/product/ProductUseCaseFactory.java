package br.com.fiap.tech.challenge.application.usecase.product;

import br.com.fiap.tech.challenge.application.gateway.ProductReaderGateway;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductUseCaseFactory {

    public static FindProductByUUIDUseCase findProductByUUIDService(ProductReaderGateway reader) {
        return new FindProductByUUIDUseCaseImpl(reader);
    }

}