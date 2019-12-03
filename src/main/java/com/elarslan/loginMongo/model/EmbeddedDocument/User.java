package com.elarslan.loginMongo.model.EmbeddedDocument;


import com.elarslan.loginMongo.model.base.DataEntity;
import com.elarslan.loginMongo.validator.constraint.ValidPassword;
import lombok.*;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Created by ersin on 01.12.2019.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("user")
public class User extends DataEntity implements Serializable{

    @NotNull(message = "Please enter the name.")
    private String name;

    @NotBlank(message = "Please enter the surname.")
    private String surname;

    @Indexed(unique = true)
    @NotBlank(message = "Please enter an email.")
    @Email(message = "Invalid email.", regexp = "^(.+)@(.+)$")
    private String email;

    @NotBlank(message = "Please enter address.")
    private Address address;

    @NotBlank(message = "Please enter country.")
    private Country country;

    private int userStatusCode;

    private int loginModuleCode;

    private LocalDateTime createdTime;

    private LocalDate updatedTime;

    private LocalDateTime mailSentTime;

    private String authenticationToken;


    @Size(min = 8, max = 30)
    @ValidPassword
    private String password;
}
