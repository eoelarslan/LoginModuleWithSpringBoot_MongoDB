package com.elarslan.loginMongo;

import com.elarslan.loginMongo.config.MongoConfig;
import com.elarslan.loginMongo.model.ReferencedDocument.EmailAddress;
import com.elarslan.loginMongo.model.ReferencedDocument.Person;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.index.Index;
import org.springframework.data.mongodb.core.index.IndexInfo;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.CoreMatchers.is;

import static org.junit.Assert.assertThat;

/**
 * Created by ersin on 3.12.2019.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = MongoConfig.class)
public class ReferencedDocumentMongoTemplate {
    @Autowired
    private MongoTemplate mongoTemplate;
    @Before
    public void testSetup() {
        if (!mongoTemplate.collectionExists(Person.class)) {
            mongoTemplate.createCollection(Person.class);
        }
    }

    @After
    public void tearDown() {
        mongoTemplate.dropCollection(Person.class);
    }
    @Test
    public void whenSavingUserWithEmailAddress_thenUserandEmailAddressSaved() {
        final Person person = new Person();
        person.setName("Brendan");
        final EmailAddress emailAddress = new EmailAddress();
        emailAddress.setValue("b@gmail.com");
        person.setEmailAddress(emailAddress);
        mongoTemplate.insert(person);

        assertThat(mongoTemplate.findOne(Query.query(Criteria.where("name").is("Brendan")), Person.class).getEmailAddress().getValue(), is("b@gmail.com"));
    }
}
