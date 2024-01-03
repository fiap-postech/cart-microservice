package br.com.fiap.tech.challenge.adapter.repository;

import br.com.fiap.tech.challenge.adapter.dto.CustomerDTO;

import java.util.Optional;

public interface CustomerReaderRepository {
    Optional<CustomerDTO> readByDocument(String document);
}
