package br.com.fiap.tech.challenge.application.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class CreateSingleProductDTO extends CreateProductDTO {
    @Serial
    private static final long serialVersionUID = 1464909268054662495L;
}
