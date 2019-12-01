package com.elarslan.loginMongo.controller.dto.requestdto;


import com.elarslan.loginMongo.model.Address;
import com.elarslan.loginMongo.model.Country;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RegisterRequestDTO {

    private String name;
    private String surname;
    private String email;
    private String password;
    private String rePassword;
    private String postalAddress;
    private Address address;
    private Country country;
}
