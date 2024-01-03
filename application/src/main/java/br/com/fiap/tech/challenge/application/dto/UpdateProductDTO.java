package br.com.fiap.tech.challenge.application.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class UpdateProductDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 3075479575539176960L;

    @NotBlank
    private String id;
    private String name;
    private String description;
    private String image;
    private BigDecimal price;
    private Boolean enabled;
}
