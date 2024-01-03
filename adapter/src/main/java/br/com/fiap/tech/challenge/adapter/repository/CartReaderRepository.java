package br.com.fiap.tech.challenge.adapter.repository;

import br.com.fiap.tech.challenge.adapter.dto.CartDTO;

public interface CartReaderRepository {

    CartDTO readById(String id);

}