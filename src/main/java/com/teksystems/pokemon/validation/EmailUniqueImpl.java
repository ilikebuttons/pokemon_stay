package com.teksystems.pokemon.validation;

import com.teksystems.pokemon.database.dao.UserDAO;
import com.teksystems.pokemon.database.entity.User;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmailUniqueImpl implements ConstraintValidator<EmailUnique, String> {

    public static final Logger LOG = LoggerFactory.getLogger(EmailUniqueImpl.class);

    @Autowired
    private UserDAO userDao;

    @Override
    public void initialize(EmailUnique constraintAnnotation) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        boolean valid = true;
        if ( StringUtils.isEmpty(value) ) {
            return true;
        }

        User user = userDao.findByEmail(value);

        return ( user == null );
    }
}