package br.com.fiap.tech.challenge.application.usecase.cart;

import br.com.fiap.tech.challenge.application.gateway.CartReaderGateway;
import br.com.fiap.tech.challenge.application.gateway.CartWriterGateway;
import br.com.fiap.tech.challenge.application.usecase.product.FindProductByUUIDUseCase;
import br.com.fiap.tech.challenge.application.dto.AddCartItemDTO;
import br.com.fiap.tech.challenge.enterprise.entity.Cart;
import br.com.fiap.tech.challenge.enterprise.entity.CartItem;
import br.com.fiap.tech.challenge.enterprise.valueobject.Quantity;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

import static java.util.UUID.fromString;

@RequiredArgsConstructor
class AddCartItemUseCaseImpl implements AddCartItemUseCase {

    private final CartReaderGateway cartReaderGateway;
    private final CartWriterGateway writerService;
    private final FindProductByUUIDUseCase findProductByUUIDUseCase;

    @Override
    public Cart add(UUID uuid, AddCartItemDTO dto) {
        var cart = cartReaderGateway.readById(uuid);

        var item = CartItem.builder()
                .product(findProductByUUIDUseCase.get(fromString(dto.getProductId())))
                .quantity(Quantity.of(dto.getQuantity()))
                .build();

        return writerService.write(cart.addItem(item));
    }
}