package org.example.openStore.modelo;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.math.BigDecimal;

public class PositivePriceValidator implements ConstraintValidator<PositivePrice, BigDecimal> {

    @Override
    public boolean isValid(BigDecimal value, ConstraintValidatorContext context) {
        if (value == null) return false;
        return value.compareTo(BigDecimal.ZERO) > 0;
    }
}
