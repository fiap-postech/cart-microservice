package br.com.fiap.tech.challenge.adapter.repository;

import br.com.fiap.tech.challenge.adapter.dto.CartDTO;

public interface CartWriterRepository {

    CartDTO write(CartDTO cart);

}