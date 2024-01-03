package br.com.fiap.tech.challenge.enterprise.valueobject;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.experimental.Accessors;

import java.io.Serial;

@Accessors(fluent = true)
@Getter
public class EmailRegistration extends ValueObject {
    @Serial
    private static final long serialVersionUID = -7027977210412190271L;

    @NotBlank
    @Email
    private final String email;

    private EmailRegistration(String email) {
        this.email = email;

        validate();
    }

    public static EmailRegistration of(String email) {
        return new EmailRegistration(email);
    }
}
