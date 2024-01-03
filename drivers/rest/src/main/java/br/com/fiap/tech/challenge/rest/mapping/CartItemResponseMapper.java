package br.com.fiap.tech.challenge.rest.mapping;

import br.com.fiap.tech.challenge.adapter.dto.CartItemDTO;
import br.com.fiap.tech.challenge.adapter.dto.ProductDTO;
import br.com.fiap.tech.challenge.rest.resource.response.CartItemResponse;
import br.com.fiap.tech.challenge.rest.resource.response.ProductResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public abstract class CartItemResponseMapper {

    @Autowired
    private ProductResponseMapper productResponseMapper;

    @Mapping(target = "product", source = "product", qualifiedByName = "getProductResponse")
    abstract CartItemResponse toCartItemResponse(CartItemDTO source);


    @Named("getProductResponse")
    ProductResponse getProductResponse(ProductDTO dto) {
        return productResponseMapper.toResponse(dto);
    }

}
