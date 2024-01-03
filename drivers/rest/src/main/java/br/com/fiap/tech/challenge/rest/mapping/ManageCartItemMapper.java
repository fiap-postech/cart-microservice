package br.com.fiap.tech.challenge.rest.mapping;

import br.com.fiap.tech.challenge.application.dto.AddCartItemDTO;
import br.com.fiap.tech.challenge.application.dto.RemoveCartItemDTO;
import br.com.fiap.tech.challenge.application.dto.UpdateCartItemDTO;
import br.com.fiap.tech.challenge.rest.resource.request.AddCartItemRequest;
import br.com.fiap.tech.challenge.rest.resource.request.RemoveCartItemRequest;
import br.com.fiap.tech.challenge.rest.resource.request.UpdateCartItemRequest;
import org.mapstruct.Mapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface ManageCartItemMapper {

    AddCartItemDTO toDTO(AddCartItemRequest request);

    UpdateCartItemDTO toDTO(UpdateCartItemRequest request);

    RemoveCartItemDTO toDTO(RemoveCartItemRequest request);

}
