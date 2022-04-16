package com.teksystems.pokemon.formbean;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@ToString
public class RegisterFormBean {

    // Null if creating, populated with userId if editing
    Integer Id;

    //@Pattern(regexp = "[a-z0-9]+@[a-z]+\\.[a-z]{2,3}", message = "Invalid email format") //TODO
    //@NotBlank(message = "Email is required")
    String email;

    @NotBlank(message = "Choose a name for your team!")
    String teamName;

    @Length(min = 8, message = "Password must be at least 8 characters")
    @NotBlank(message = "Password is required")
    String password;

    @NotBlank(message = "Must confirm password")
    String confirmPassword;
}