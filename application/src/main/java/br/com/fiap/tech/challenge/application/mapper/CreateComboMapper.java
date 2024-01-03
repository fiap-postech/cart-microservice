package br.com.fiap.tech.challenge.application.mapper;

import br.com.fiap.tech.challenge.application.dto.CreateComboProductDTO;
import br.com.fiap.tech.challenge.enterprise.entity.Combo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;


@Mapper(uses = {
        CommonMapper.class,
        CreateBeverageMapper.class,
        CreateSideDishMapper.class,
        CreateSandwichMapper.class
})
public interface CreateComboMapper {

    CreateComboMapper INSTANCE = Mappers.getMapper(CreateComboMapper.class);

    @Mapping(target = "price", source = "price", qualifiedByName = "getPrice")
    @Mapping(target = "image", source = "image", qualifiedByName = "getImage")
    @Mapping(target = "beverage", ignore = true)
    @Mapping(target = "sideDish", ignore = true)
    @Mapping(target = "sandwich", ignore = true)
    @Mapping(target = "uuid", ignore = true)
    Combo toCombo(CreateComboProductDTO source);

}
