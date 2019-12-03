package com.elarslan.loginMongo.controller;

import com.elarslan.loginMongo.controller.dto.base.GenericResponseDTO;
import com.elarslan.loginMongo.model.ReferencedDocument.EmailAddress;
import com.elarslan.loginMongo.model.ReferencedDocument.Person;
import com.elarslan.loginMongo.repository.PersonRepository;
import com.elarslan.loginMongo.util.enums.MessageStatus;
import com.elarslan.loginMongo.util.helper.MessageHelper;
import javassist.tools.web.BadHttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Created by ersin on 3.12.2019.
 */
@RestController
@RequestMapping(value = "/")
public class PersonController {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private MessageHelper messageHelper;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private MongoOperations mongoOperations;

    @PostMapping("/createPerson")
    public ResponseEntity createUser(@Valid @RequestBody Person person) throws BadHttpRequest {

        Person person1 = new Person(person.getName(),person.getAge(),person.getEmailAddress());

        //Both fetching can be applied.
        //mongoOperations.insert(person1);
        personRepository.insert(person);

        return ResponseEntity.ok().body(new GenericResponseDTO<>(HttpStatus.OK,
                messageHelper.getMessageByMessageStatus(MessageStatus.USER_CREATED, null), null));
    }
}

