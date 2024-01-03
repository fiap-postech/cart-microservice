package br.com.fiap.tech.challenge.driven.product.mapper;

import br.com.fiap.tech.challenge.adapter.dto.ComboDTO;
import br.com.fiap.tech.challenge.adapter.dto.ProductDTO;
import br.com.fiap.tech.challenge.driven.product.response.ComboResponse;
import br.com.fiap.tech.challenge.driven.product.response.ProductResponse;
import org.mapstruct.Mapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface ProductClientResponseMapper {

    default ProductDTO toDTO(Object dto) {
        if (dto instanceof ComboResponse comboResponse){
            return toComboDTO(comboResponse);
        }

        return toSingleDTO((ProductResponse) dto);
    }

    ProductDTO toSingleDTO(ProductResponse response);

    ComboDTO toComboDTO(ComboResponse response);
}
