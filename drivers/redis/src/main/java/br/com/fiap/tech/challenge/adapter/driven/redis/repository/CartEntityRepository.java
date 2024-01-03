package br.com.fiap.tech.challenge.adapter.driven.redis.repository;

import br.com.fiap.tech.challenge.adapter.driven.redis.model.CartEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartEntityRepository extends CrudRepository<CartEntity, String> {

}