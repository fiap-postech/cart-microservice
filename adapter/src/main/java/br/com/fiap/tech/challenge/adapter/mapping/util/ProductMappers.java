package br.com.fiap.tech.challenge.adapter.mapping.util;

import br.com.fiap.tech.challenge.adapter.dto.ComboDTO;
import br.com.fiap.tech.challenge.adapter.dto.ProductDTO;
import br.com.fiap.tech.challenge.adapter.mapping.BeverageMapper;
import br.com.fiap.tech.challenge.adapter.mapping.ComboMapper;
import br.com.fiap.tech.challenge.adapter.mapping.DessertMapper;
import br.com.fiap.tech.challenge.adapter.mapping.SandwichMapper;
import br.com.fiap.tech.challenge.adapter.mapping.SideDishMapper;
import br.com.fiap.tech.challenge.enterprise.entity.Beverage;
import br.com.fiap.tech.challenge.enterprise.entity.Combo;
import br.com.fiap.tech.challenge.enterprise.entity.Dessert;
import br.com.fiap.tech.challenge.enterprise.entity.Product;
import br.com.fiap.tech.challenge.enterprise.entity.Sandwich;
import br.com.fiap.tech.challenge.enterprise.entity.SideDish;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductMappers {

    public static Product toProductDomain(ProductDTO dto) {
        return switch (dto.getCategory()){
            case SANDWICH -> SandwichMapper.INSTANCE.toDomain(dto);
            case BEVERAGE -> BeverageMapper.INSTANCE.toDomain(dto);
            case SIDE_DISH -> SideDishMapper.INSTANCE.toDomain(dto);
            case DESSERT -> DessertMapper.INSTANCE.toDomain(dto);
            case COMBO -> ComboMapper.INSTANCE.toDomain((ComboDTO) dto);
        };
    }

    public static ProductDTO toProductDTO(Product product) {
        return switch (product.category()){
            case SANDWICH -> SandwichMapper.INSTANCE.toDTO((Sandwich) product);
            case BEVERAGE -> BeverageMapper.INSTANCE.toDTO((Beverage) product);
            case SIDE_DISH -> SideDishMapper.INSTANCE.toDTO((SideDish) product);
            case DESSERT -> DessertMapper.INSTANCE.toDTO((Dessert) product);
            case COMBO -> ComboMapper.INSTANCE.toDTO((Combo) product);
        };
    }
}
