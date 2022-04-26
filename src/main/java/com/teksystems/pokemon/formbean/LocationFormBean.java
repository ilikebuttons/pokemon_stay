package com.teksystems.pokemon.formbean;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@ToString
public class LocationFormBean {

    // Null if creating, populated with userId if editing
    Integer Id;

    @Pattern(regexp = "[a-z0-9]+@[a-z]+\\.[a-z]{2,3}", message = "Invalid email format") // works after changing input type from email to text
    @NotBlank(message = "Email is required")
    String name;

    @NotBlank(message = "Choose a name for your team!")
    String description;

    @Length(min = 8, message = "Password must be at least 8 characters")
    @Length(max = 20, message = "Password can't be more than 20 characters")
    @NotBlank(message = "Password is required")
    String password;

    @NotBlank(message = "Must confirm password")    //TODO check for matching confirm password
    String confirmPassword;
}