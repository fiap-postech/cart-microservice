package br.com.fiap.tech.challenge.application.usecase.cart;

import br.com.fiap.tech.challenge.application.gateway.CartReaderGateway;
import br.com.fiap.tech.challenge.application.gateway.CartWriterGateway;
import br.com.fiap.tech.challenge.application.usecase.customer.FindCustomerByUUIDUseCase;
import br.com.fiap.tech.challenge.application.usecase.product.FindProductByUUIDUseCase;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CartUseCaseFactory {

    public static FindCartByUUIDUseCase findCartByUUIDService(CartReaderGateway reader) {
        return new FindCartByUUIDUseCaseImpl(reader);
    }

    public static CreateCartUseCase createCartService(FindCustomerByUUIDUseCase useCase, CartWriterGateway writer) {
        return new CreateCartUseCaseImpl(useCase, writer);
    }

    public static AddCartItemUseCase addCartItemService(CartReaderGateway reader, CartWriterGateway writer, FindProductByUUIDUseCase findProductByUUIDUseCase) {
        return new AddCartItemUseCaseImpl(reader, writer, findProductByUUIDUseCase);
    }

    public static UpdateCartItemUseCase updateCartItemService(CartReaderGateway reader, CartWriterGateway writer, FindProductByUUIDUseCase findProductByUUIDUseCase) {
        return new UpdateCartItemUseCaseImpl(reader, writer, findProductByUUIDUseCase);
    }

    public static RemoveCartItemUseCase removeCartItemUseCase(CartReaderGateway useCase, CartWriterGateway presenter) {
        return new RemoveCartItemUseCaseImpl(useCase, presenter);
    }

    public static CloseCartUseCase closeCartService(CartReaderGateway reader, CartWriterGateway writer) {
        return new CloseCartUseCaseImpl(reader, writer);
    }
}
