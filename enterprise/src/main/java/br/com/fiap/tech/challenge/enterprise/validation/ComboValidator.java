package br.com.fiap.tech.challenge.enterprise.validation;

import br.com.fiap.tech.challenge.enterprise.entity.Combo;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import static java.util.Objects.nonNull;

public class ComboValidator implements ConstraintValidator<ComboValid, Combo> {

    @Override
    public boolean isValid(Combo value, ConstraintValidatorContext context) {
        return nonNull(value) &&
                nonNull(value.beverage()) &&
                nonNull(value.sandwich()) &&
                nonNull(value.sideDish());
    }
}
