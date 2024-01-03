package br.com.fiap.tech.challenge.adapter.mapping;

import br.com.fiap.tech.challenge.adapter.dto.CartDTO;
import br.com.fiap.tech.challenge.adapter.dto.CartItemDTO;
import br.com.fiap.tech.challenge.adapter.dto.CustomerDTO;
import br.com.fiap.tech.challenge.enterprise.entity.Cart;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.math.BigDecimal;
import java.util.List;

import static br.com.fiap.tech.challenge.util.MoneyConstants.CURRENCY_PRECISION;
import static br.com.fiap.tech.challenge.util.MoneyConstants.CURRENCY_ROUNDING_MODE;

@Mapper(uses = { CustomerMapper.class, CartItemMapper.class, CommonMapper.class })
public interface CartMapper {

    CartMapper INSTANCE = Mappers.getMapper(CartMapper.class);

    @Mapping(target = "uuid", source = "id", qualifiedByName = "generateUuid")
    Cart toDomain(CartDTO dto);

    @Mapping(target = "id", expression = "java(cart.uuid().toString())")
    @Mapping(target = "items", source = "cart", qualifiedByName = "getListCartItemDTO")
    @Mapping(target = "total", source = "cart", qualifiedByName = "getTotal")
    @Mapping(target = "subtotal", source = "cart", qualifiedByName = "getSubtotal")
    @Mapping(target = "discount", source = "cart", qualifiedByName = "getDiscount")
    @Mapping(target = "customer", source = "cart", qualifiedByName = "getCustomerDTO")
    CartDTO toDTO(Cart cart);

    @Named("getCustomerDTO")
    static CustomerDTO mapCustomerDTO(Cart cart) {
        return CustomerMapper.INSTANCE.toDTO(cart.customer());
    }

    @Named("getListCartItemDTO")
    static List<CartItemDTO> mapListCartItemDTO(Cart cart) {
        return cart.items().stream()
                .map(CartItemMapper.INSTANCE::toDTO)
                .toList();
    }

    @Named("getTotal")
    static BigDecimal getTotal(Cart source) {
        return source.items().stream()
                .map(i -> i.total().amount().getNumberStripped())
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .setScale(CURRENCY_PRECISION, CURRENCY_ROUNDING_MODE);
    }

    @Named("getSubtotal")
    static BigDecimal getSubtotal(Cart source) {
        return source.items().stream()
                .map(i -> i.subtotal().amount().getNumberStripped())
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .setScale(CURRENCY_PRECISION, CURRENCY_ROUNDING_MODE);
    }

    @Named("getDiscount")
    static BigDecimal getDiscount(Cart source) {
        return source.items().stream()
                .map(i -> i.discount().amount().getNumberStripped())
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .setScale(CURRENCY_PRECISION, CURRENCY_ROUNDING_MODE);
    }
}
