package br.com.fiap.tech.challenge.launcher.configuration;

import br.com.fiap.tech.challenge.application.gateway.CartReaderGateway;
import br.com.fiap.tech.challenge.application.gateway.CartWriterGateway;
import br.com.fiap.tech.challenge.application.gateway.CustomerReaderGateway;
import br.com.fiap.tech.challenge.application.gateway.ProductReaderGateway;
import br.com.fiap.tech.challenge.application.usecase.cart.AddCartItemUseCase;
import br.com.fiap.tech.challenge.application.usecase.cart.CartUseCaseFactory;
import br.com.fiap.tech.challenge.application.usecase.cart.CloseCartUseCase;
import br.com.fiap.tech.challenge.application.usecase.cart.CreateCartUseCase;
import br.com.fiap.tech.challenge.application.usecase.cart.FindCartByUUIDUseCase;
import br.com.fiap.tech.challenge.application.usecase.cart.RemoveCartItemUseCase;
import br.com.fiap.tech.challenge.application.usecase.cart.UpdateCartItemUseCase;
import br.com.fiap.tech.challenge.application.usecase.customer.CustomerUseCaseFactory;
import br.com.fiap.tech.challenge.application.usecase.customer.FindCustomerByUUIDUseCase;
import br.com.fiap.tech.challenge.application.usecase.product.FindProductByUUIDUseCase;
import br.com.fiap.tech.challenge.application.usecase.product.ProductUseCaseFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfiguration {
    @Bean
    public FindProductByUUIDUseCase findProductByUUIDService(ProductReaderGateway reader) {
        return ProductUseCaseFactory.findProductByUUIDService(reader);
    }

    @Bean
    public FindCustomerByUUIDUseCase findCustomerByUUIDUseCase(CustomerReaderGateway reader) {
        return CustomerUseCaseFactory.findCustomerByUUIDUseCase(reader);
    }

    @Bean
    public FindCartByUUIDUseCase findCartByUUIDService(CartReaderGateway reader) {
        return CartUseCaseFactory.findCartByUUIDService(reader);
    }

    @Bean
    public CreateCartUseCase createCartService(FindCustomerByUUIDUseCase useCase, CartWriterGateway writer) {
        return CartUseCaseFactory.createCartService(useCase, writer);
    }

    @Bean
    public AddCartItemUseCase addCartItemService(CartReaderGateway reader, CartWriterGateway writer, FindProductByUUIDUseCase findProductByUUIDUseCase) {
        return CartUseCaseFactory.addCartItemService(reader, writer, findProductByUUIDUseCase);
    }

    @Bean
    public UpdateCartItemUseCase updateCartItemService(CartReaderGateway reader, CartWriterGateway writer, FindProductByUUIDUseCase findProductByUUIDUseCase) {
        return CartUseCaseFactory.updateCartItemService(reader, writer, findProductByUUIDUseCase);
    }

    @Bean
    public RemoveCartItemUseCase removeCartItemUseCase(CartReaderGateway useCase, CartWriterGateway presenter) {
        return CartUseCaseFactory.removeCartItemUseCase(useCase, presenter);
    }

    @Bean
    public CloseCartUseCase closeCartService(CartReaderGateway reader, CartWriterGateway writer) {
        return CartUseCaseFactory.closeCartService(reader, writer);
    }
}