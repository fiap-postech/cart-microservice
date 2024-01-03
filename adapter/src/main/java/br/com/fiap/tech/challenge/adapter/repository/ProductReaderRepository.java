package br.com.fiap.tech.challenge.adapter.repository;

import br.com.fiap.tech.challenge.adapter.dto.ProductDTO;

import java.util.Optional;

public interface ProductReaderRepository {
    Optional<ProductDTO> readById(String id);

}
