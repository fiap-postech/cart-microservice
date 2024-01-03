package br.com.fiap.tech.challenge.adapter.mapping;

import br.com.fiap.tech.challenge.adapter.dto.ProductDTO;
import br.com.fiap.tech.challenge.enterprise.entity.Sandwich;
import br.com.fiap.tech.challenge.enterprise.enums.ProductCategory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.math.BigDecimal;

import static br.com.fiap.tech.challenge.util.Mappings.discountToBigDecimalConverter;
import static br.com.fiap.tech.challenge.util.Mappings.imageToStringConverter;
import static br.com.fiap.tech.challenge.util.Mappings.priceToBigDecimalConverter;

@Mapper(uses = {CommonMapper.class})
public interface SandwichMapper {

    SandwichMapper INSTANCE = Mappers.getMapper(SandwichMapper.class);

    @Mapping(target = "uuid", source = "id", qualifiedByName = "generateUuid")
    @Mapping(target = "price", source = "price", qualifiedByName = "getPrice")
    @Mapping(target = "image", source = "image", qualifiedByName = "getImage")
    Sandwich toDomain(ProductDTO request);

    @Mapping(target = "id", expression = "java(source.uuid().toString())")
    @Mapping(target = "name", expression = "java(source.name())")
    @Mapping(target = "description", expression = "java(source.description())")
    @Mapping(target = "fullPrice", source = "source", qualifiedByName = "getFullPrice")
    @Mapping(target = "discount", source = "source", qualifiedByName = "getDiscount")
    @Mapping(target = "enabled", expression = "java(source.enabled())")
    @Mapping(target = "category", source = "source", qualifiedByName = "getCategory")
    @Mapping(target = "price", source = "source", qualifiedByName = "priceToBigDecimal")
    @Mapping(target = "image", source = "source", qualifiedByName = "imageToStringConverter")
    ProductDTO toDTO(Sandwich source);

    @Named("priceToBigDecimal")
    static BigDecimal priceToBigDecimal(Sandwich source) {
        return priceToBigDecimalConverter(source.price());
    }

    @Named("getFullPrice")
    static BigDecimal getFullPrice(Sandwich source) {
        return priceToBigDecimalConverter(source.fullPrice());
    }

    @Named("getDiscount")
    static BigDecimal getDiscount(Sandwich source) {
        return discountToBigDecimalConverter(source.discount());
    }

    @Named("getCategory")
    static ProductCategory getCategory(Sandwich source) {
        return source.category();
    }

    @Named("imageToStringConverter")
    static String imageConverter(Sandwich source) {
        return imageToStringConverter(source.image());
    }

}
