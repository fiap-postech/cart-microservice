package br.com.fiap.tech.challenge.application.fixture;

import br.com.fiap.tech.challenge.enterprise.entity.Cart;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.instancio.Instancio;
import org.instancio.Model;

import java.util.List;
import java.util.UUID;

import static br.com.fiap.tech.challenge.application.fixture.CartItemFixture.createCartItemModel;
import static br.com.fiap.tech.challenge.application.fixture.CustomerFixture.createCustomerModel;
import static br.com.fiap.tech.challenge.application.fixture.Fixture.create;
import static org.instancio.Select.field;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CartFixture {

    public static final UUID UUID_CART = UUID.fromString("0c407fe3-c120-46b9-97c5-48b4ed059bae");

    public static Model<Cart> createCartModel() {
        var items = List.of(create(createCartItemModel()));

        return Instancio.of(Cart.class)
                .set(field(Cart::customer), create(createCustomerModel()))
                .set(field(Cart::items), items)
                .set(field(Cart::uuid), UUID_CART)
                .toModel();
    }
}
