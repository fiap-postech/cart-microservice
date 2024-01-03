package br.com.fiap.tech.challenge.enterprise.valueobject;

import br.com.fiap.tech.challenge.enterprise.entity.CartItem;
import br.com.fiap.tech.challenge.enterprise.entity.Product;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.experimental.Accessors;

import java.io.Serial;

@Getter
@Accessors(fluent = true)
@EqualsAndHashCode(callSuper = true)
public class PurchaseItem extends ValueObject {
    @Serial
    private static final long serialVersionUID = -2071891141308689952L;

    @NotNull
    @Valid
    private final Product product;

    @NotNull
    @Valid
    private final Quantity quantity;

    @NotNull
    private final Price fullPrice;

    @NotNull
    private final Price price;

    @NotNull
    private final Discount discount;

    @Builder(toBuilder = true)
    public PurchaseItem(@NotNull Product product,
                        @NotNull Quantity quantity,
                        @NotNull Price fullPrice,
                        @NotNull Price price,
                        @NotNull Discount discount) {
        this.product = product;
        this.quantity = quantity;
        this.fullPrice = fullPrice;
        this.price = price;
        this.discount = discount;

        validate();
    }

    public Price subTotal() {
        return product().fullPrice().multiply(quantity());
    }

    public Discount totalDiscount() {
        return product().discount().multiply(quantity());
    }

    public Price total() {
        return product().price().multiply(quantity());
    }

    public static PurchaseItem of(CartItem item) {
        return PurchaseItem.builder()
                .discount(item.product().discount())
                .fullPrice(item.product().fullPrice())
                .price(item.product().price())
                .quantity(item.quantity())
                .product(item.product())
                .build();
    }
}
