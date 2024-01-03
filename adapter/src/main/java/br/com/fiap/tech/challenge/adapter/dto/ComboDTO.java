package br.com.fiap.tech.challenge.adapter.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class ComboDTO extends ProductDTO {
    @Serial
    private static final long serialVersionUID = 7002042207857288411L;

    private ProductDTO beverage;
    private ProductDTO sandwich;
    private ProductDTO sideDish;
}
