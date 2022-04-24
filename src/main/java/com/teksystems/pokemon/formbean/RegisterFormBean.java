package com.teksystems.pokemon.formbean;

import com.teksystems.pokemon.validation.EmailUnique;
import com.teksystems.pokemon.validation.MatchingFields;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@MatchingFields(f1Name = "password", f2Name = "confirmPassword", message = "Passwords must match")
public class RegisterFormBean {

    // Null if creating, populated with userId if editing
    Integer Id;

    @EmailUnique(message = "Email already exists in database")
    @Email(message = "Invalid email format")
    @NotBlank(message = "Email is required")
    String email;

    @NotBlank(message = "Choose a name for your team!")
    String teamName;

    @Length(min = 8, message = "Password must be at least 8 characters")
    @Length(max = 20, message = "Password can't be more than 20 characters")
    @NotBlank(message = "Password is required")
    String password;

    @NotBlank(message = "Must confirm password")    //TODO check for matching confirm password
    String confirmPassword;
}