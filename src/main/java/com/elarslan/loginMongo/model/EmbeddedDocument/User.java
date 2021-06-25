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

    //...

    @Field("username")
    private String name;


    //...
}
