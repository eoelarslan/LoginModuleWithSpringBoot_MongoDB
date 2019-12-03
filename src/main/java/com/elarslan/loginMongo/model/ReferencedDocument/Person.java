package com.elarslan.loginMongo.model.ReferencedDocument;

import com.elarslan.loginMongo.annotation.CascadeSave;
import com.querydsl.core.annotations.QueryEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * Created by ersin on 3.12.2019.
 */
@Getter
@Setter
@QueryEntity
@Document("person")
@CompoundIndexes({ @CompoundIndex(name = "email_age", def = "{'email.id' : 1, 'age': 1}") })
public class Person {

    @Id
    private String id;

    @Indexed(direction = IndexDirection.ASCENDING)
    private String name;

    @Indexed(direction = IndexDirection.ASCENDING)
    private Integer age;

    @DBRef
    @CascadeSave
    @Field("emailAddress")
    private EmailAddress emailAddress;

    @Transient
    private Integer yearOfBirth;

    public Person() {
    }

    public Person(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    @PersistenceConstructor
    public Person(final String name, @Value("#root.age ?: 0") final Integer age, final EmailAddress emailAddress) {
        this.name = name;
        this.age = age;
        this.emailAddress = emailAddress;
    }

}
