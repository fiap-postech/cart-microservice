package br.com.fiap.tech.challenge.adapter.driven.redis.mapping;

import br.com.fiap.tech.challenge.adapter.driven.redis.model.CustomerEntity;
import br.com.fiap.tech.challenge.adapter.dto.CustomerDTO;
import br.com.fiap.tech.challenge.enterprise.valueobject.Document;
import br.com.fiap.tech.challenge.enterprise.valueobject.EmailRegistration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface RedisCustomerMapper {

    @Mapping(target = "email", source = "email", qualifiedByName = "toEmailVO")
    @Mapping(target = "document", source = "document", qualifiedByName = "toDocumentVO")
    CustomerEntity toEntity(CustomerDTO dto);

    @Mapping(target = "email", expression = "java(entity.getEmail().email())")
    @Mapping(target = "document", expression = "java(entity.getDocument().document())")
    CustomerDTO toDTO(CustomerEntity entity);

    @Named("toEmailVO")
    static EmailRegistration toEmailVO(String email) {
        return EmailRegistration.of(email);
    }

    @Named("toDocumentVO")
    static Document toDocumentVO(String document) {
        return Document.of(document);
    }
}
