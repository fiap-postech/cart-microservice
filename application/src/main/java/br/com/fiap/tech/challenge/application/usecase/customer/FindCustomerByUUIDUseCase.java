package br.com.fiap.tech.challenge.application.usecase.customer;

import br.com.fiap.tech.challenge.enterprise.entity.Customer;

import java.util.Optional;
import java.util.UUID;

public interface FindCustomerByUUIDUseCase {

    Optional<Customer> get(UUID uuid);

}