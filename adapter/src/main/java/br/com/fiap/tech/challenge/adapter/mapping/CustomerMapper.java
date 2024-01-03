package br.com.fiap.tech.challenge.adapter.mapping;

import br.com.fiap.tech.challenge.adapter.dto.CustomerDTO;
import br.com.fiap.tech.challenge.enterprise.entity.Customer;
import br.com.fiap.tech.challenge.enterprise.valueobject.Document;
import br.com.fiap.tech.challenge.enterprise.valueobject.EmailRegistration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {CommonMapper.class})
public interface CustomerMapper {

    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    @Mapping(target = "id", expression = "java(source.uuid().toString())")
    @Mapping(target = "name", expression = "java(source.name())")
    @Mapping(target = "email", expression = "java(source.toEmail())")
    @Mapping(target = "document", expression = "java(source.toDocument())")
    @Mapping(target = "enabled", expression = "java(source.enabled())")
    CustomerDTO toDTO(Customer source);

    @Mapping(target = "email", source = "source", qualifiedByName = "getEmail")
    @Mapping(target = "document", source = "source", qualifiedByName = "getDocument")
    @Mapping(target = "uuid", source = "id", qualifiedByName = "generateUuid")
    Customer toDomain(CustomerDTO source);

    @Named("getEmail")
    static EmailRegistration getEmail(CustomerDTO source) {
        return EmailRegistration.of(source.getEmail());
    }

    @Named("getDocument")
    static Document getDocument(CustomerDTO source) {
        return Document.of(source.getDocument());
    }

}
