package br.com.fiap.tech.challenge.driven.customer.service;

import br.com.fiap.tech.challenge.adapter.dto.CustomerDTO;
import br.com.fiap.tech.challenge.adapter.repository.CustomerReaderRepository;
import br.com.fiap.tech.challenge.driven.customer.client.CustomerClient;
import br.com.fiap.tech.challenge.driven.customer.mapper.CustomerClientResponseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerReaderRepository {

    private final CustomerClient customerClient;
    private final CustomerClientResponseMapper customerClientResponseMapper;

    @Override
    public Optional<CustomerDTO> readByID(String id) {
        var response = customerClient.getById(id);

        if (response.getStatusCode().is2xxSuccessful()) {
            return Optional.of(customerClientResponseMapper.toDTO(response.getBody()));
        }

        return Optional.empty();
    }
}
