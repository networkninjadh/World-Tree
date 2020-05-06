   package com.howtech;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.howtech.models.Person;
import com.howtech.repositories.PersonRepository;

@EntityScan(basePackages = "com.howtech.models")
@EnableTransactionManagement
@EnableNeo4jRepositories
@SpringBootApplication 
public class AccessingDataNeo4jApplication implements CommandLineRunner{

	@Autowired
	private PersonRepository personRepository;
  public static void main(String[] args) {
    SpringApplication.run(AccessingDataNeo4jApplication.class, args);
  }

@Override
public void run(String... args) throws Exception {
	Person me = new Person();
	me.setFirstName("damond_test");
	me.setMiddleName("dontrail");
	me.setLastName("howard");
	Person childsMother = new Person();
	childsMother.setFirstName("Mom");
	childsMother.setMiddleName("middlename");
	childsMother.setLastName("lastname");
	
	Person child = new Person();
	child.setMother(childsMother);
	child.setFirstName("baaby");
	child.setMiddleName("middle");
	child.setLastName("last");
	childsMother.setChild(child);
	me.setChild(child);
	Person mom = new Person();
	mom.setFirstName("Ella_test");
	mom.setMiddleName("mae");
	mom.setLastName("howard");
	Person dad = new Person();
	dad.setFirstName("paul_test");
	dad.setMiddleName("montrelle");
	dad.setLastName("Drake");
	me.setFather(dad);
	me.setMother(mom);
	personRepository.save(me);
	
	System.out.println(me.toString());
}
  
  
}