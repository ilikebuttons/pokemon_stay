package com.teksystems.pokemon.validation;

import com.fasterxml.jackson.databind.util.BeanUtil;
import org.apache.commons.beanutils.BeanUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class MatchingFieldsImpl implements ConstraintValidator<MatchingFields, Object> {

    private String f1Name;
    private String f2Name;
    private String message;

    @Override
    public void initialize(MatchingFields constraintAnnotation) {
        f1Name = constraintAnnotation.f1Name();
        f2Name = constraintAnnotation.f2Name();
        message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(final Object value, final ConstraintValidatorContext context) {
        boolean valid = true;

        try {
            final Object FIRST = BeanUtils.getProperty(value, f1Name);
            final Object SECOND = BeanUtils.getProperty(value, f2Name);

            valid = FIRST == null && SECOND == null ||
                    FIRST != null && FIRST.equals(SECOND);

        } catch (Exception ignored) {}

        if (!valid){
            context.buildConstraintViolationWithTemplate(message)
                    .addPropertyNode(f1Name)
                    .addConstraintViolation()
                    .disableDefaultConstraintViolation();
        }

        return valid;
    }
}