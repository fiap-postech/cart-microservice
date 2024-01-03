package br.com.fiap.tech.challenge.driven.customer.service;

import br.com.fiap.tech.challenge.adapter.dto.CustomerDTO;
import br.com.fiap.tech.challenge.adapter.repository.CustomerReaderRepository;
import br.com.fiap.tech.challenge.driven.customer.client.CustomerClient;
import br.com.fiap.tech.challenge.driven.customer.mapper.CustomerResponseMapper;
import br.com.fiap.tech.challenge.enterprise.valueobject.Document;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerReaderRepository {

    private final CustomerClient customerClient;
    private final CustomerResponseMapper customerResponseMapper;

    @Override
    public Optional<CustomerDTO> readByDocument(String documentValue) {
        var document = Document.of(documentValue);

        var response = customerClient.getByDocument(document.document());

        if (response.getStatusCode().is2xxSuccessful()) {
            return Optional.of(customerResponseMapper.toDTO(response.getBody()));
        }

        return Optional.empty();
    }
}
