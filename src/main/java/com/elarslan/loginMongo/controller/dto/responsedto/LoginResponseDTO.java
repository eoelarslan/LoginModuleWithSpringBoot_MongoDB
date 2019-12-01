package com.elarslan.loginMongo.controller.dto.responsedto;


import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponseDTO {

    private String email;
    private String name;
    private String surname;
}
