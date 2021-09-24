package com.netodevel.demo.repository;

import com.netodevel.demo.model.Person;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends MongoRepository<Person, String> {

    List<Person> findByFirstNameIgnoringCase(String firstName);

    List<Person> findByLastNameIgnoringCase(String lastName);

}