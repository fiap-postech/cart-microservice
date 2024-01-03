package br.com.fiap.tech.challenge.adapter.driven.redis.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ComboProductEntity extends ProductEntity {

    private ProductEntity sandwich;
    private ProductEntity beverage;
    private ProductEntity sideDish;
}