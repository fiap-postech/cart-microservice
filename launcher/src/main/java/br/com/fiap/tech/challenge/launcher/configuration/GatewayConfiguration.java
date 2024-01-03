package br.com.fiap.tech.challenge.launcher.configuration;

import br.com.fiap.tech.challenge.adapter.gateway.cart.CartGatewayFactory;
import br.com.fiap.tech.challenge.adapter.gateway.customer.CustomerGatewayFactory;
import br.com.fiap.tech.challenge.adapter.gateway.product.ProductGatewayFactory;
import br.com.fiap.tech.challenge.adapter.repository.CartReaderRepository;
import br.com.fiap.tech.challenge.adapter.repository.CartWriterRepository;
import br.com.fiap.tech.challenge.adapter.repository.CloseCartRepository;
import br.com.fiap.tech.challenge.adapter.repository.CustomerReaderRepository;
import br.com.fiap.tech.challenge.adapter.repository.ProductReaderRepository;
import br.com.fiap.tech.challenge.application.gateway.CartReaderGateway;
import br.com.fiap.tech.challenge.application.gateway.CartWriterGateway;
import br.com.fiap.tech.challenge.application.gateway.CustomerReaderGateway;
import br.com.fiap.tech.challenge.application.gateway.ProductReaderGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfiguration {

    @Bean
    public ProductReaderGateway productReaderGateway(ProductReaderRepository repository) {
        return ProductGatewayFactory.productReaderGateway(repository);
    }

    @Bean
    public CustomerReaderGateway customerReaderGateway(CustomerReaderRepository repository) {
        return CustomerGatewayFactory.customerReaderGateway(repository);
    }

    @Bean
    public CartWriterGateway cartWriterGateway(CartWriterRepository cartWriterRepository, CloseCartRepository closeCartRepository) {
        return CartGatewayFactory.cartWriterGateway(cartWriterRepository, closeCartRepository);
    }

    @Bean
    public CartReaderGateway cartReaderGateway(CartReaderRepository repository) {
        return CartGatewayFactory.cartReaderGateway(repository);
    }

}
