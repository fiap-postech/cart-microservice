package br.com.fiap.tech.challenge.application.fixture;

import br.com.fiap.tech.challenge.enterprise.entity.CartItem;
import br.com.fiap.tech.challenge.enterprise.valueobject.Quantity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.instancio.Instancio;
import org.instancio.Model;

import static br.com.fiap.tech.challenge.application.fixture.Fixture.create;
import static br.com.fiap.tech.challenge.application.fixture.ProductFixture.createSandwichModel;
import static org.instancio.Select.field;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CartItemFixture {

    public static Model<CartItem> createCartItemModel() {
        return Instancio.of(CartItem.class)
                .set(field(CartItem::product), create(createSandwichModel()))
                .set(field(CartItem::quantity), Quantity.of(1))
                .toModel();
    }
}
