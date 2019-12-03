package com.elarslan.loginMongo.model.EmbeddedDocument;

import lombok.Data;

/**
 * Created by ersin on 1.12.2019.
 */
@Data
public class Country {

    private int id;

    private String name;

    private String shortName;

    private String continent;

}
