package br.com.fiap.tech.challenge.adapter.mapping;

import br.com.fiap.tech.challenge.adapter.dto.ProductDTO;
import br.com.fiap.tech.challenge.enterprise.entity.Beverage;
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
public interface BeverageMapper {

    BeverageMapper INSTANCE = Mappers.getMapper(BeverageMapper.class);

    @Mapping(target = "uuid", source = "id", qualifiedByName = "generateUuid")
    @Mapping(target = "price", source = "price", qualifiedByName = "getPrice")
    @Mapping(target = "image", source = "image", qualifiedByName = "getImage")
    Beverage toDomain(ProductDTO dto);

    @Mapping(target = "id", expression = "java(source.uuid().toString())")
    @Mapping(target = "price", source = "source", qualifiedByName = "priceToBigDecimal")
    @Mapping(target = "image", source = "source", qualifiedByName = "imageToStringConverter")
    @Mapping(target = "name", expression = "java(source.name())")
    @Mapping(target = "description", expression = "java(source.description())")
    @Mapping(target = "fullPrice", source = "source", qualifiedByName = "getFullPrice")
    @Mapping(target = "discount", source = "source", qualifiedByName = "getDiscount")
    @Mapping(target = "enabled", expression = "java(source.enabled())")
    @Mapping(target = "category", source = "source", qualifiedByName = "getCategory")
    ProductDTO toDTO(Beverage source);

    @Named("priceToBigDecimal")
    static BigDecimal priceToBigDecimal(Beverage source) {
        return priceToBigDecimalConverter(source.price());
    }

    @Named("getFullPrice")
    static BigDecimal getFullPrice(Beverage source) {
        return priceToBigDecimalConverter(source.fullPrice());
    }

    @Named("getDiscount")
    static BigDecimal getDiscount(Beverage source) {
        return discountToBigDecimalConverter(source.discount());
    }

    @Named("getCategory")
    static ProductCategory getCategory(Beverage source) {
        return source.category();
    }

    @Named("imageToStringConverter")
    static String imageConverter(Beverage source) {
        return imageToStringConverter(source.image());
    }
}
