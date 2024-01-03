package br.com.fiap.tech.challenge.enterprise.entity;

import br.com.fiap.tech.challenge.enterprise.enums.ProductCategory;
import br.com.fiap.tech.challenge.enterprise.validation.ComboValid;
import br.com.fiap.tech.challenge.enterprise.valueobject.Discount;
import br.com.fiap.tech.challenge.enterprise.valueobject.Image;
import br.com.fiap.tech.challenge.enterprise.valueobject.Price;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.util.UUID;
import java.util.stream.Stream;

import static java.util.Objects.isNull;

@Getter
@Accessors(fluent = true)
@ComboValid
public class Combo extends Product {
    @Serial
    private static final long serialVersionUID = 2866585082574769968L;

    private final Sandwich sandwich;
    private final Beverage beverage;
    private final SideDish sideDish;

    @Builder(toBuilder = true)
    @SuppressWarnings("squid:S107")
    protected Combo(
            @Builder.ObtainVia(method = "uuid") UUID uuid,
            @Builder.ObtainVia(method = "name") String name,
            @Builder.ObtainVia(method = "description") String description,
            @Builder.ObtainVia(method = "price") Price price,
            @Builder.ObtainVia(method = "image") Image image,
            Sandwich sandwich,
            Beverage beverage,
            SideDish sideDish,
            @Builder.ObtainVia(method = "enabled") boolean enabled
    ) {
        super(
                uuid,
                name,
                description,
                price,
                image,
                currentEnabled(enabled, sandwich, beverage, sideDish)
        );

        this.sandwich = sandwich;
        this.beverage = beverage;
        this.sideDish = sideDish;
    }

    @Override
    public ProductCategory category() {
        return ProductCategory.COMBO;
    }

    @Override
    public Product enable() {
        return toBuilder()
                .enabled(true)
                .build();
    }

    @Override
    public Product disable() {
        return toBuilder()
                .enabled(false)
                .build();
    }

    @Override
    public Price fullPrice() {
        return Stream.of(beverage, sideDish, sandwich)
                .map(Product::price)
                .reduce(Price.min(), Price::add);
    }

    @Override
    public Discount discount() {
        return Discount.of(fullPrice().subtract(price()).amount());
    }

    private static boolean currentEnabled(boolean enabled, Product... products) {
        if (Stream.of(products).filter(p -> !isNull(p)).map(Product::enabled).anyMatch(b -> !b)) {
            return false;
        }

        return enabled;
    }
}
