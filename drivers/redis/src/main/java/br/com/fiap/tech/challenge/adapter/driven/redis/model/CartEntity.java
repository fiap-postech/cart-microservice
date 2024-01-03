package br.com.fiap.tech.challenge.adapter.driven.redis.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RedisHash(value = "cart")
@Getter
@Setter
@ToString
public class CartEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = -1085132067646799595L;

    @Id
    private String id;
    private CustomerEntity customer;
    private List<CartItemEntity> items;
    private BigDecimal total;
    private BigDecimal subtotal;
    private BigDecimal discount;

    @TimeToLive(unit = TimeUnit.MILLISECONDS)
    private Long ttl;
}