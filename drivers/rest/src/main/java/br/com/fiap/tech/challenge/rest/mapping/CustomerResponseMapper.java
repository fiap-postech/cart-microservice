package br.com.fiap.tech.challenge.rest.mapping;

import br.com.fiap.tech.challenge.adapter.dto.CustomerDTO;
import br.com.fiap.tech.challenge.rest.resource.response.CustomerResponse;
import org.mapstruct.Mapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface CustomerResponseMapper {

    CustomerResponse toResponse(CustomerDTO dto);
}
