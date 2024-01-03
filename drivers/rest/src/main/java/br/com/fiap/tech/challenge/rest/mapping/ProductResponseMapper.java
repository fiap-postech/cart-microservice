package br.com.fiap.tech.challenge.rest.mapping;

import br.com.fiap.tech.challenge.adapter.dto.ComboDTO;
import br.com.fiap.tech.challenge.adapter.dto.ProductDTO;
import br.com.fiap.tech.challenge.rest.resource.response.ComboResponse;
import br.com.fiap.tech.challenge.rest.resource.response.ProductResponse;
import org.mapstruct.Mapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface ProductResponseMapper {

    default ProductResponse toResponse(Object dto) {
        if (dto instanceof ComboDTO comboDTO){
            return toComboResponse(comboDTO);
        }

        return toSingleResponse((ProductDTO) dto);
    }

    ProductResponse toSingleResponse(ProductDTO dto);

    ComboResponse toComboResponse(ComboDTO dto);
}
