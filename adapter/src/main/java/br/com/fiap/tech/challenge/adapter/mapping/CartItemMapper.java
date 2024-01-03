package br.com.fiap.tech.challenge.adapter.mapping;

import br.com.fiap.tech.challenge.adapter.dto.CartItemDTO;
import br.com.fiap.tech.challenge.adapter.dto.ProductDTO;
import br.com.fiap.tech.challenge.enterprise.entity.CartItem;
import br.com.fiap.tech.challenge.enterprise.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.math.BigDecimal;

import static br.com.fiap.tech.challenge.adapter.mapping.util.ProductMappers.toProductDTO;
import static br.com.fiap.tech.challenge.adapter.mapping.util.ProductMappers.toProductDomain;
import static br.com.fiap.tech.challenge.util.Mappings.discountToBigDecimalConverter;
import static br.com.fiap.tech.challenge.util.Mappings.priceToBigDecimalConverter;
import static br.com.fiap.tech.challenge.util.Mappings.quantityToIntegerConverter;

@Mapper(uses = { CommonMapper.class })
public interface CartItemMapper {

    CartItemMapper INSTANCE = Mappers.getMapper(CartItemMapper.class);

    @Mapping(target = "product", source = "product", qualifiedByName = "getProductDomain")
    @Mapping(target = "quantity", source = "quantity", qualifiedByName = "getQuantityVO")
    CartItem toDomain(CartItemDTO dto);

    @Mapping(target = "product", source = "item", qualifiedByName = "getProductDTO")
    @Mapping(target = "total", source = "item", qualifiedByName = "getTotal")
    @Mapping(target = "subtotal", source = "item", qualifiedByName = "getSubtotal")
    @Mapping(target = "discount", source = "item", qualifiedByName = "getDiscount")
    @Mapping(target = "quantity", source = "item", qualifiedByName = "getQuantity")
    CartItemDTO toDTO(CartItem item);

    @Named("getProductDomain")
    static Product mapProductDomain(ProductDTO dto) {
        return toProductDomain(dto);
    }

    @Named("getProductDTO")
    static ProductDTO mapProductDTO(CartItem item) {
        return toProductDTO(item.product());
    }

    @Named("getTotal")
    static BigDecimal getTotal(CartItem source) {
        return priceToBigDecimalConverter(source.total());
    }

    @Named("getSubtotal")
    static BigDecimal getSubtotal(CartItem source) {
        return priceToBigDecimalConverter(source.subtotal());
    }

    @Named("getDiscount")
    static BigDecimal getDiscount(CartItem source) {
        return discountToBigDecimalConverter(source.discount());
    }

    @Named("getQuantity")
    static Integer getQuantity(CartItem source) {
        return quantityToIntegerConverter(source.quantity());
    }
}
