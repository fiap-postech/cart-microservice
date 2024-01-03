package br.com.fiap.tech.challenge.driven.cart;


import br.com.fiap.tech.challenge.adapter.dto.CartDTO;
import br.com.fiap.tech.challenge.adapter.repository.CloseCartRepository;
import org.springframework.stereotype.Service;

@Service
public class CloseCartRepositoryImpl implements CloseCartRepository {
    @Override
    public void close(CartDTO dto) {
        throw new UnsupportedOperationException();
    }
}
