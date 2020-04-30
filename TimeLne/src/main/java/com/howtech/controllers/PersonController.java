package com.howtech.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.howtech.models.Person;
import com.howtech.repositories.PersonRepository;

@RestController
public class PersonController {
	
	@Autowired
	private PersonRepository personRepository;
	
	@PostMapping ("/addPerson")
	Person newPerson(@RequestBody Person person) {
		Person myPerson = new Person();
		myPerson.setFirstName(person.getFirstName());
		myPerson.setMiddleName(person.getMiddleName());
		myPerson.setLastName(person.getLastName());
		personRepository.save(person);
		return person;
	}
	
	@GetMapping("/person")
	Optional<Person> getPerson(@RequestParam(value = "id") Long id) {
		Optional<Person> person = null;
		try {
			person =  personRepository.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		return person;
	}
}
