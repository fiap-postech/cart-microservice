package br.com.fiap.tech.challenge.application.dto;

import br.com.fiap.tech.challenge.enterprise.validation.UUID;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class CreateComboProductDTO extends CreateProductDTO {
    @Serial
    private static final long serialVersionUID = 1464909268054662495L;

    @UUID
    private String sandwichId;

    @UUID
    private String beverageId;

    @UUID
    private String sideDishId;
}
