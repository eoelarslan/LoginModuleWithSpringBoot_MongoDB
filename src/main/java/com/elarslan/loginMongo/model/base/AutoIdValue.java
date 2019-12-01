package com.elarslan.loginMongo.model.base;


import lombok.Getter;
import org.springframework.data.annotation.Id;

public class AutoIdValue {

    @Getter
    @Id
    protected String id;


}
