package br.com.fiap.tech.challenge.application.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class CreateCartDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 872840129927289117L;

    @NotBlank
    private String customerId;
}
