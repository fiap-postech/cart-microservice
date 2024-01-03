package br.com.fiap.tech.challenge.application.dto;

import br.com.fiap.tech.challenge.enterprise.enums.ProductCategory;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
public abstract class CreateProductDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1464909268054662495L;

    @NotBlank
    private String name;

    @NotBlank
    private String description;

    @PositiveOrZero
    private BigDecimal price;

    @NotBlank
    private String image;

    @NotNull
    private ProductCategory category;

    private boolean enabled = true;
}
