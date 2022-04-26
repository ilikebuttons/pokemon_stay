package com.teksystems.pokemon.formbean;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import javax.validation.constraints.NotBlank;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@ToString
public class TrainerLocationBean {

    //Integer id;
    Integer location_id;
    Integer trainer_id;
    Integer pokeballs;
    Integer potions;
}