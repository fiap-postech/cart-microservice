package br.com.fiap.tech.challenge.adapter.mapping;

import br.com.fiap.tech.challenge.adapter.dto.ProductDTO;
import br.com.fiap.tech.challenge.enterprise.entity.Dessert;
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
public interface DessertMapper {

    DessertMapper INSTANCE = Mappers.getMapper(DessertMapper.class);

    @Mapping(target = "uuid", source = "id", qualifiedByName = "generateUuid")
    @Mapping(target = "price", source = "price", qualifiedByName = "getPrice")
    @Mapping(target = "image", source = "image", qualifiedByName = "getImage")
    Dessert toDomain(ProductDTO request);

    @Mapping(target = "id", source = "request", qualifiedByName = "getUuid")
    @Mapping(target = "price", source = "request", qualifiedByName = "priceToBigDecimal")
    @Mapping(target = "image", source = "request", qualifiedByName = "imageToStringConverter")
    @Mapping(target = "name", expression = "java(request.name())")
    @Mapping(target = "description", expression = "java(request.description())")
    @Mapping(target = "fullPrice", source = "request", qualifiedByName = "getFullPrice")
    @Mapping(target = "discount", source = "request", qualifiedByName = "getDiscount")
    @Mapping(target = "enabled", expression = "java(request.enabled())")
    @Mapping(target = "category", source = "request", qualifiedByName = "getCategory")
    ProductDTO toDTO(Dessert request);

    @Named("getUuid")
    static String getUuid(Dessert request) {
        return request.uuid().toString();
    }

    @Named("priceToBigDecimal")
    static BigDecimal priceToBigDecimal(Dessert request) {
        return priceToBigDecimalConverter(request.price());
    }

    @Named("getFullPrice")
    static BigDecimal getFullPrice(Dessert request) {
        return priceToBigDecimalConverter(request.fullPrice());
    }

    @Named("getDiscount")
    static BigDecimal getDiscount(Dessert request) {
        return discountToBigDecimalConverter(request.discount());
    }

    @Named("getCategory")
    static ProductCategory getCategory(Dessert request) {
        return request.category();
    }

    @Named("imageToStringConverter")
    static String imageConverter(Dessert request) {
        return imageToStringConverter(request.image());
    }
}
