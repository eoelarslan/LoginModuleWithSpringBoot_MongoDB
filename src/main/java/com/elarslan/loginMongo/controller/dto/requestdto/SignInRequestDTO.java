package com.elarslan.loginMongo.controller.dto.requestdto;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class SignInRequestDTO {

    private String email;
    private String password;
}
