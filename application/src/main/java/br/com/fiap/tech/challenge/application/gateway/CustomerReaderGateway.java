package br.com.fiap.tech.challenge.application.gateway;

import br.com.fiap.tech.challenge.enterprise.entity.Customer;
import br.com.fiap.tech.challenge.enterprise.valueobject.Document;

import java.util.Optional;
import java.util.UUID;

public interface CustomerReaderGateway {

    Optional<Customer> readByDocument(Document document);
}