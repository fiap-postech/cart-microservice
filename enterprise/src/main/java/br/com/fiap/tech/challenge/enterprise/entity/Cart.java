package br.com.fiap.tech.challenge.enterprise.entity;

import br.com.fiap.tech.challenge.enterprise.valueobject.Price;
import br.com.fiap.tech.challenge.enterprise.error.ApplicationError;
import br.com.fiap.tech.challenge.exception.ApplicationException;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.apache.commons.lang3.ObjectUtils.defaultIfNull;

@Getter
@Accessors(fluent = true)
@EqualsAndHashCode(callSuper = true)
@ToString
public class Cart extends Entity {

    @Serial
    private static final long serialVersionUID = -7454461774303685197L;

    private final Customer customer;

    private final List<CartItem> items;

    @Builder(toBuilder = true)
    public Cart(@Builder.ObtainVia(method = "uuid") UUID uuid, List<CartItem> items, Customer customer) {
        super(uuid);
        this.customer = customer;
        this.items = defaultIfNull(items, new ArrayList<>());
    }

    public Cart addItem(CartItem cartItem) {
        if (!cartItem.product().enabled()) {
            throw new ApplicationException(ApplicationError.CART_ITEM_NOT_AVAILABLE, this.uuid(), cartItem.product().uuid());
        }

        var newItems = new ArrayList<>(items);
        items.stream()
                .filter(i -> i.product().uuid().equals(cartItem.product().uuid()))
                .findFirst()
                .ifPresentOrElse(i -> {
                    var newItem = i.toBuilder()
                            .product(cartItem.product())
                            .build()
                            .incrementQuantity(cartItem.quantity());

                    newItems.set(items.indexOf(i), newItem);
                }, () -> newItems.add(cartItem));

        return toBuilder()
                .items(newItems)
                .build();
    }

    public Cart updateItem(CartItem cartItem) {
        var newItems = new ArrayList<>(items);
        items.stream()
                .filter(i -> i.product().uuid().equals(cartItem.product().uuid()))
                .findFirst()
                .ifPresent(i -> {
                    var newItem = i.toBuilder()
                            .quantity(cartItem.quantity())
                            .product(cartItem.product())
                            .build();

                    newItems.set(items.indexOf(i), newItem);
                });

        return toBuilder()
                .items(newItems)
                .build();
    }

    public Cart removeItem(UUID productId) {
        var newItems = new ArrayList<>(items);

        newItems.removeIf(i -> i.product().uuid().equals(productId));

        return toBuilder()
                .items(newItems)
                .build();
    }

    public Price total() {
        return items().stream()
                .map(CartItem::total)
                .reduce(Price.min(), Price::add);
    }
}