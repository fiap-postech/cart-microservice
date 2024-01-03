package br.com.fiap.tech.challenge.adapter.driven.redis.model;

import br.com.fiap.tech.challenge.enterprise.enums.ProductCategory;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@ToString
public class ProductEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1613694852732417701L;

    private String id;
    private String name;
    private String description;
    private BigDecimal price;
    private String image;
    private ProductCategory category;
    private Boolean enabled;
}