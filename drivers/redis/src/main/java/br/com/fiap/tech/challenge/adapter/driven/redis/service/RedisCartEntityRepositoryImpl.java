package br.com.fiap.tech.challenge.adapter.driven.redis.service;

import br.com.fiap.tech.challenge.adapter.driven.redis.mapping.RedisCartMapper;
import br.com.fiap.tech.challenge.adapter.driven.redis.repository.CartEntityRepository;
import br.com.fiap.tech.challenge.adapter.dto.CartDTO;
import br.com.fiap.tech.challenge.adapter.repository.CartReaderRepository;
import br.com.fiap.tech.challenge.adapter.repository.CartWriterRepository;
import br.com.fiap.tech.challenge.exception.ApplicationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static br.com.fiap.tech.challenge.enterprise.error.ApplicationError.CART_NOT_FOUND_BY_UUID;

@Service
@RequiredArgsConstructor
public class RedisCartEntityRepositoryImpl implements CartReaderRepository, CartWriterRepository {

    private final CartEntityRepository repository;
    private final RedisCartMapper mapper;

    @Override
    public CartDTO readById(String id) {
        return repository.findById(id)
                .map(mapper::toDTO)
                .orElseThrow(() -> new ApplicationException(CART_NOT_FOUND_BY_UUID, id));
    }

    @Override
    public CartDTO write(CartDTO cart) {
        return mapper.toDTO(repository.save(mapper.toEntity(cart)));
    }

    @Override
    public void deleteById(String id) {
        repository.deleteById(id);
    }
}