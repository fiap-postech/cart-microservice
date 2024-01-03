package br.com.fiap.tech.challenge.application.usecase.cart;

import br.com.fiap.tech.challenge.application.gateway.CartReaderGateway;
import br.com.fiap.tech.challenge.application.gateway.CartWriterGateway;
import br.com.fiap.tech.challenge.application.usecase.product.FindProductByUUIDUseCase;
import br.com.fiap.tech.challenge.application.dto.UpdateCartItemDTO;
import br.com.fiap.tech.challenge.enterprise.entity.Cart;
import br.com.fiap.tech.challenge.enterprise.entity.CartItem;
import br.com.fiap.tech.challenge.enterprise.valueobject.Quantity;
import lombok.AllArgsConstructor;

import java.util.UUID;

import static java.util.UUID.fromString;

@AllArgsConstructor
class UpdateCartItemUseCaseImpl implements UpdateCartItemUseCase {

    private CartReaderGateway cartReaderGateway;
    private CartWriterGateway writerService;
    private FindProductByUUIDUseCase findProductByUUIDUseCase;

    @Override
    public Cart update(UUID uuid, UpdateCartItemDTO dto) {
        var cart = cartReaderGateway.readById(uuid);

        var item = CartItem.builder()
                .product(findProductByUUIDUseCase.get(fromString(dto.getProductId())))
                .quantity(Quantity.of(dto.getQuantity()))
                .build();

        return writerService.write(cart.updateItem(item));
    }
}