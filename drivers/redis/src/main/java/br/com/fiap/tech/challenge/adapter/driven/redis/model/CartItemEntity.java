package br.com.fiap.tech.challenge.adapter.driven.redis.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@ToString
public class CartItemEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = -304755728066875717L;

    private ProductEntity product;
    private BigDecimal price;
    private BigDecimal discount;
    private int quantity;
}