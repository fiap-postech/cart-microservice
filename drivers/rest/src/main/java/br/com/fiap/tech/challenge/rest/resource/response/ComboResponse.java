package br.com.fiap.tech.challenge.rest.resource.response;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class ComboResponse extends ProductResponse {
    @Serial
    private static final long serialVersionUID = 7002042207857288411L;

    private ProductResponse beverage;
    private ProductResponse sandwich;
    private ProductResponse sideDish;
}
