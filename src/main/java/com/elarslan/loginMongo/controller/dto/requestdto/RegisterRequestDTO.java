package com.elarslan.loginMongo.controller.dto.requestdto;


import com.elarslan.loginMongo.model.Address;
import com.elarslan.loginMongo.model.Country;
import com.elarslan.loginMongo.validator.constraint.ValidPassword;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@ToString
public class RegisterRequestDTO {

    @NotNull(message = "Please enter the name.")
    private String name;

    @NotBlank(message = "Please enter the surname.")
    private String surname;

    @Email(message = "Invalid email.", regexp = "^(.+)@(.+)$")
    private String email;

    @NotNull(message = "Please enter address.")
    private Address address;

    @NotNull(message = "Please enter country.")
    private Country country;

/*    @Size(min = 8, max = 30)
    @ValidPassword*/
    private String password;

    private String rePassword;
}
