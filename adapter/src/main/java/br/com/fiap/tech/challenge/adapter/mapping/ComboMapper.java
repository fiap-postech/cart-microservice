package br.com.fiap.tech.challenge.adapter.mapping;

import br.com.fiap.tech.challenge.adapter.dto.ComboDTO;
import br.com.fiap.tech.challenge.adapter.dto.ProductDTO;
import br.com.fiap.tech.challenge.enterprise.entity.Combo;
import br.com.fiap.tech.challenge.enterprise.enums.ProductCategory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.math.BigDecimal;

import static br.com.fiap.tech.challenge.util.Mappings.discountToBigDecimalConverter;
import static br.com.fiap.tech.challenge.util.Mappings.imageToStringConverter;
import static br.com.fiap.tech.challenge.util.Mappings.priceToBigDecimalConverter;

@Mapper(uses = {
        CommonMapper.class,
        BeverageMapper.class,
        SideDishMapper.class,
        SandwichMapper.class
})
public interface ComboMapper {

    ComboMapper INSTANCE = Mappers.getMapper(ComboMapper.class);

    @Mapping(target = "price", source = "price", qualifiedByName = "getPrice")
    @Mapping(target = "image", source = "image", qualifiedByName = "getImage")
    @Mapping(target = "uuid", source = "id", qualifiedByName = "generateUuid")
    Combo toDomain(ComboDTO source);

    @Mapping(target = "price", source = "combo", qualifiedByName = "priceToBigDecimalCombo")
    @Mapping(target = "image", source = "combo", qualifiedByName = "imageToStringConverterCombo")
    @Mapping(target = "name", expression = "java(combo.name())")
    @Mapping(target = "description", expression = "java(combo.description())")
    @Mapping(target = "fullPrice", source = "combo", qualifiedByName = "getFullPrice")
    @Mapping(target = "discount", source = "combo", qualifiedByName = "getDiscount")
    @Mapping(target = "enabled", expression = "java(combo.enabled())")
    @Mapping(target = "id", expression = "java(combo.uuid().toString())")
    @Mapping(target = "category", source = "combo", qualifiedByName = "getCategory")
    @Mapping(target = "beverage", source = "combo", qualifiedByName = "toBeverage")
    @Mapping(target = "sideDish", source = "combo", qualifiedByName = "toSideDish")
    @Mapping(target = "sandwich", source = "combo", qualifiedByName = "toSandwich")
    ComboDTO toDTO(Combo combo);

    @Named("priceToBigDecimalCombo")
    static BigDecimal priceToBigDecimal(Combo source) {
        return priceToBigDecimalConverter(source.price());
    }

    @Named("imageToStringConverterCombo")
    static String imageConverter(Combo source) {
        return imageToStringConverter(source.image());
    }

    @Named("getFullPrice")
    static BigDecimal getFullPrice(Combo combo) {
        return priceToBigDecimalConverter(combo.fullPrice());
    }

    @Named("getDiscount")
    static BigDecimal getDiscount(Combo combo) {
        return discountToBigDecimalConverter(combo.discount());
    }

    @Named("getCategory")
    static ProductCategory getCategory(Combo combo) {
        return ProductCategory.COMBO;
    }

    @Named("toBeverage")
    static ProductDTO mapBeverage(Combo combo) {
        return BeverageMapper.INSTANCE.toDTO(combo.beverage());
    }

    @Named("toSandwich")
    static ProductDTO mapSandwich(Combo combo) {
        return SandwichMapper.INSTANCE.toDTO(combo.sandwich());
    }

    @Named("toSideDish")
    static ProductDTO mapSideDish(Combo combo) {
        return SideDishMapper.INSTANCE.toDTO(combo.sideDish());
    }
}
