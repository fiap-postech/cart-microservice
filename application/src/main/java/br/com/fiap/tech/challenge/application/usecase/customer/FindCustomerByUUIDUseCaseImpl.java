package br.com.fiap.tech.challenge.application.usecase.customer;

import br.com.fiap.tech.challenge.application.gateway.CustomerReaderGateway;
import br.com.fiap.tech.challenge.enterprise.entity.Customer;
import lombok.AllArgsConstructor;

import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
class FindCustomerByUUIDUseCaseImpl implements FindCustomerByUUIDUseCase {

    private CustomerReaderGateway customerReaderGateway;

    @Override
    public Optional<Customer> get(UUID uuid) {
        return customerReaderGateway.readByID(uuid);
    }
}