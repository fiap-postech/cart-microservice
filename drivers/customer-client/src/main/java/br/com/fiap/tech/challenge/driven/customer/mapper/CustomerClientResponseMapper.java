package br.com.fiap.tech.challenge.driven.customer.mapper;

import br.com.fiap.tech.challenge.adapter.dto.CustomerDTO;
import br.com.fiap.tech.challenge.driven.customer.response.CustomerResponse;
import org.mapstruct.Mapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface CustomerClientResponseMapper {

    CustomerDTO toDTO(CustomerResponse dto);
}
