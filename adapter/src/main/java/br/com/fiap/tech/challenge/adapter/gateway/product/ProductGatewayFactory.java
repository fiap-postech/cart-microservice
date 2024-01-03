package br.com.fiap.tech.challenge.adapter.gateway.product;

import br.com.fiap.tech.challenge.adapter.repository.ProductReaderRepository;
import br.com.fiap.tech.challenge.application.gateway.ProductReaderGateway;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductGatewayFactory {

    public static ProductReaderGateway productReaderGateway(ProductReaderRepository repository){
        return new ProductReaderGatewayImpl(repository);
    }
}
