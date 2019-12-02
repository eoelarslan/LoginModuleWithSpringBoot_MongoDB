package com.elarslan.loginMongo.repository;


import com.elarslan.loginMongo.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


/**
 * Created by ersin on 01.12.2019.
 */
@Repository
public interface UserRepository extends MongoRepository<User, Long> {

    Optional<User> findByEmail(String email);

    void deleteUserByEmail(String email);

    /*List<Footballer> findByName(String name);

    List<Footballer> findBySurname(String surname);

    Footballer findFirstByOrderByWorthDesc();

    Footballer findByNameAndSurname(String name, String surname);

    void deleteFootballerByNameAndSurname(String name, String surname);*/
}
