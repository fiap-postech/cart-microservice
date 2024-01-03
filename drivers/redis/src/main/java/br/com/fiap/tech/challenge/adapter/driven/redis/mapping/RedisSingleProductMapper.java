package br.com.fiap.tech.challenge.adapter.driven.redis.mapping;

import br.com.fiap.tech.challenge.adapter.driven.redis.model.ProductEntity;
import br.com.fiap.tech.challenge.adapter.dto.ProductDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface RedisSingleProductMapper {

    ProductEntity toEntity(ProductDTO dto);

    @Mapping(target = "discount", ignore = true)
    @Mapping(target = "fullPrice", ignore = true)
    ProductDTO toDTO(ProductEntity entity);

}
