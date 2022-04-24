package com.teksystems.pokemon.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({TYPE, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = MatchingFieldsImpl.class)
@Documented
public @interface MatchingFields {

    String f1Name();
    String f2Name();

    String message() default "Fields must match";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    @Target({TYPE, ANNOTATION_TYPE})
    @Retention(RUNTIME)
    @Documented
    @interface List
    {
        MatchingFields[] value();
    }
}