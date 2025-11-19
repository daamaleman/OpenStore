package org.example.openStore.modelo;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PositivePriceValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface PositivePrice {
    String message() default "El precio debe ser mayor que 0";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
