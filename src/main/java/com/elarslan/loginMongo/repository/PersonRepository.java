package com.elarslan.loginMongo.repository;

import com.elarslan.loginMongo.model.ReferencedDocument.Person;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by ersin on 3.12.2019.
 */
@Repository
public interface PersonRepository extends MongoRepository<Person, String>{
}
