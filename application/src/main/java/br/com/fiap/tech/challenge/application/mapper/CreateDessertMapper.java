package br.com.fiap.tech.challenge.application.mapper;

import br.com.fiap.tech.challenge.application.dto.CreateSingleProductDTO;
import br.com.fiap.tech.challenge.enterprise.entity.Dessert;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {CommonMapper.class})
public interface CreateDessertMapper {

    CreateDessertMapper INSTANCE = Mappers.getMapper(CreateDessertMapper.class);

    @Mapping(target = "uuid", ignore = true)
    @Mapping(target = "price", source = "price", qualifiedByName = "getPrice")
    @Mapping(target = "image", source = "image", qualifiedByName = "getImage")
    Dessert toDessert(CreateSingleProductDTO request);
}
