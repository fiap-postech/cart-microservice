package br.com.fiap.tech.challenge.adapter.dto;

import br.com.fiap.tech.challenge.enterprise.enums.ProductCategory;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;


@Getter
@Setter
public class ProductDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1464909268054662495L;

    private String id;
    private String name;
    private String description;
    private String image;
    private BigDecimal price;
    private BigDecimal fullPrice;
    private BigDecimal discount;
    private ProductCategory category;
    private Boolean enabled;
}
