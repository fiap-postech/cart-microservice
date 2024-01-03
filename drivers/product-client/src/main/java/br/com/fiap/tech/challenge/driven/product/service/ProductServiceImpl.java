package br.com.fiap.tech.challenge.driven.product.service;

import br.com.fiap.tech.challenge.adapter.dto.ProductDTO;
import br.com.fiap.tech.challenge.adapter.repository.ProductReaderRepository;
import br.com.fiap.tech.challenge.driven.product.client.ProductClient;
import br.com.fiap.tech.challenge.driven.product.mapper.ProductClientResponseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductReaderRepository {

    private final ProductClient productClient;
    private final ProductClientResponseMapper productClientResponseMapper;

    @Override
    public Optional<ProductDTO> readById(String id) {
        var response = productClient.getByUUID(id);

        if (response.getStatusCode().is2xxSuccessful()) {
            return Optional.of(productClientResponseMapper.toDTO(response.getBody()));
        }

        return Optional.empty();
    }
}
