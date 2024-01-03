package br.com.fiap.tech.challenge.driven.product.response;

import br.com.fiap.tech.challenge.enterprise.enums.ProductCategory;
import br.com.fiap.tech.challenge.rest.common.response.Response;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.math.BigDecimal;


@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = ProductResponse.class, name = "single"),
        @JsonSubTypes.Type(value = ComboResponse.class, name = "combo")
})
public class ProductResponse extends Response {
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
