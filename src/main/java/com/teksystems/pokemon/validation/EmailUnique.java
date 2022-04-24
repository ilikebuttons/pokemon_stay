package com.teksystems.pokemon.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = EmailUniqueImpl.class)
@Target({METHOD, FIELD})
@Retention(RUNTIME)
public @interface EmailUnique {

    String message() default "Email already in database";

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}