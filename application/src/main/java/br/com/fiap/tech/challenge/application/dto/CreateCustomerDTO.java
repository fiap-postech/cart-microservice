package br.com.fiap.tech.challenge.application.dto;

import br.com.fiap.tech.challenge.enterprise.validation.DocumentCustomer;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class CreateCustomerDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 3410518373165012648L;

    @NotBlank
    private String name;

    @NotBlank
    @Email
    private String email;

    @DocumentCustomer
    private String document;
}
