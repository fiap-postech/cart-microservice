package br.com.fiap.tech.challenge.application.dto;

import br.com.fiap.tech.challenge.enterprise.validation.UUID;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class RemoveCartItemDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 6023377331491012701L;

    @UUID
    private String productId;

}
