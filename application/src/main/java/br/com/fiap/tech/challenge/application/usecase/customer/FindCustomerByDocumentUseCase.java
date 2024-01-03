package br.com.fiap.tech.challenge.application.usecase.customer;

import br.com.fiap.tech.challenge.enterprise.entity.Customer;

import java.util.Optional;

public interface FindCustomerByDocumentUseCase {

    Optional<Customer> get(String document);

}
