package com.elarslan.loginMongo.model.ReferencedDocument;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by ersin on 3.12.2019.
 */
@Getter
@Setter
@Document("EmailAddress")
public class EmailAddress {
    @Id
    private String id;
    private String value;
}
