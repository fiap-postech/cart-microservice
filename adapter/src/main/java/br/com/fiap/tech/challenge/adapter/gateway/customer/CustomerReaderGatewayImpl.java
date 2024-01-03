package br.com.fiap.tech.challenge.adapter.gateway.customer;

import br.com.fiap.tech.challenge.adapter.mapping.CustomerMapper;
import br.com.fiap.tech.challenge.adapter.repository.CustomerReaderRepository;
import br.com.fiap.tech.challenge.application.gateway.CustomerReaderGateway;
import br.com.fiap.tech.challenge.enterprise.entity.Customer;
import lombok.RequiredArgsConstructor;

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
class CustomerReaderGatewayImpl implements CustomerReaderGateway {
    private static final CustomerMapper MAPPER = CustomerMapper.INSTANCE;

    private final CustomerReaderRepository readerRepository;

    @Override
    public Optional<Customer> readByID(UUID id) {
        return readerRepository.readByID(id.toString())
                .map(MAPPER::toDomain);
    }
}
