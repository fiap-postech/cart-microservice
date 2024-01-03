package br.com.fiap.tech.challenge.application.util;

import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validation;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import static java.util.Objects.nonNull;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Validations {

    public static void validate(Object element) {
        try (var factory = Validation.buildDefaultValidatorFactory()){
            var validator = factory.getValidator();

            var violations = validator.validate(element);

            if (nonNull(violations) && !violations.isEmpty()){
                throw new ConstraintViolationException(violations);
            }
        }
    }
}
