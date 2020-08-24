package com.howtech;

import java.util.Arrays;
import java.util.Stack;

import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.Config;
import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;
import org.neo4j.driver.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.google.common.collect.Sets;
import com.howtech.models.FamilyTree;
import com.howtech.models.Gender;
import com.howtech.models.Person;
import com.howtech.models.User;
import com.howtech.repositories.FamilyTreeRepository;
import com.howtech.repositories.PersonRepository;
import com.howtech.security.ApplicationUserRole;

@EntityScan(basePackages = "com.howtech.models")
@EnableTransactionManagement
@EnableNeo4jRepositories
@SpringBootApplication 
public class AccessingDataNeo4jApplication implements CommandLineRunner{

	
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private PersonRepository personRepository;
	
	@Autowired
	private FamilyTreeRepository familyTreeRepository;
	
  public static void main(String[] args) {
    SpringApplication.run(AccessingDataNeo4jApplication.class, args);
  }

@Override
public void run(String... args) throws Exception {
	
		String graphenedbURL = System.getenv("GRAPHENEDB_BOLT_URL");
	    String graphenedbUser = System.getenv("GRAPHENEDB_BOLT_USER");
	    String graphenedbPass = System.getenv("GRAPHENEDB_BOLT_PASSWORD");

	    Config.ConfigBuilder builder = Config.builder().withEncryption();
	    Config config = builder.build();

	    Driver driver = GraphDatabase.driver( graphenedbURL, AuthTokens.basic( graphenedbUser, graphenedbPass ), config );

	    Session session = driver.session();
	
	
	
	//Stack<Person> familyMembers = new Stack<Person>();
	/**
	Person me = new Person();
	Person uncle  = new Person("Uncle", "Uncle", "Uncle", Gender.MALE);

	//create grandfather1
	Person grandFather = new Person("grand-father-1", "Dads", "Grand Father 1", Gender.MALE);
	grandFather.setChild(new Person("Uncle", "OR", "Aunt", Gender.FEMALE), familyMembers);
	grandFather.setChild(uncle, familyMembers);
	//create grandmother1
	Person grandMother = new Person("grand-mother-1", "Dads", "Grand Mother 1", Gender.FEMALE);
	grandMother.setChild(uncle, familyMembers);
	//create grandmother2
	Person grandMother2 = new Person("grand-mother-2", "Mothers", "Grand Mother 2", Gender.FEMALE);
	//create grandfather2
	Person grandFather2 = new Person("grand-father-2", "Mothers", "Grand Father 2", Gender.MALE);
	grandFather.setChild(new Person("Uncle", "OR", "AUNT", Gender.FEMALE), familyMembers);
	grandFather.setChild(new Person("Uncle", "OR", "AUNT", Gender.FEMALE), familyMembers);
		//add another child
	//create mother
	Person mother = new Person("My Mother", "Mother", "My Mother", Gender.FEMALE);
	mother.setFather(grandFather2, familyMembers);
	mother.setMother(grandMother2, familyMembers);
	mother.setChild(new Person("Mothers", "First", "Mother's Child", Gender.MALE), familyMembers);
		//add another child
	//create father
	Person father = new Person("Father", "Father", "Father", Gender.MALE);
	father.setFather(grandFather, familyMembers);
	father.setMother(grandMother, familyMembers);
	//create brother
	father.setChild(new Person("Fathers", "First", "Father's Child", Gender.MALE), familyMembers);
	father.setChild(new Person("Fathers", "Second", "Father's Child", Gender.FEMALE), familyMembers);
	//create child
	me.setMother(mother, familyMembers);
	me.setFather(father, familyMembers);
	
	Person wife = new Person("Wife", "", "Wife", Gender.FEMALE);
	Person baby = new Person("MY Baby", "", "Baby", Gender.MALE);
	baby.setMother(wife, familyMembers);
	baby.setFather(me, familyMembers);
	//wife.setChild(baby);
	//me.setChild(baby);
	
	personRepository.save(me);
	
**/
	/**
	 
	 * TODO: figure out how to run the app on the server
	 */
//	
//	Person me = new Person();
//	Person uncle  = new Person("Uncle", "Uncle", "Uncle", Gender.MALE);
//
//	//create grandfather1
//	Person grandFather = new Person("grand-father-1", "Dads", "Grand Father 1", Gender.MALE);
//	grandFather.setChild(new Person("Uncle", "OR", "Aunt", Gender.FEMALE));
//	grandFather.setChild(uncle);
//	//create grandmother1
//	Person grandMother = new Person("grand-mother-1", "Dads", "Grand Mother 1", Gender.FEMALE);
//	grandMother.setChild(uncle);
//	//create grandmother2
//	Person grandMother2 = new Person("grand-mother-2", "Mothers", "Grand Mother 2", Gender.FEMALE);
//	//create grandfather2
//	Person grandFather2 = new Person("grand-father-2", "Mothers", "Grand Father 2", Gender.MALE);
//	grandFather.setChild(new Person("Uncle", "OR", "AUNT", Gender.FEMALE));
//	grandFather.setChild(new Person("Uncle", "OR", "AUNT", Gender.FEMALE));
//		//add another child
//	//create mother
//	Person mother = new Person("My Mother", "Mother", "My Mother", Gender.FEMALE);
//	mother.setFather(grandFather2);
//	mother.setMother(grandMother2);
//	mother.setChild(new Person("Mothers", "First", "Mother's Child", Gender.MALE));
//		//add another child
//	//create father
//	Person father = new Person("Father", "Father", "Father", Gender.MALE);
//	father.setFather(grandFather);
//	father.setMother(grandMother);
//	//create brother
//	father.setChild(new Person("Fathers", "First", "Father's Child", Gender.MALE));
//	father.setChild(new Person("Fathers", "Second", "Father's Child", Gender.FEMALE));
//	//create child
//	me.setMother(mother);
//	me.setFather(father);
//	
//	Person wife = new Person("Wife", "", "Wife", Gender.FEMALE);
//	Person baby = new Person("MY Baby", "", "Baby", Gender.MALE);
//	baby.setMother(wife);
//	baby.setFather(me);
//	//wife.setChild(baby);
//	//me.setChild(baby);
//	FamilyTree myTree = new FamilyTree(me);
//	familyTreeRepository.save(myTree);
//	
//	User userMe = new User("networkninjadh", passwordEncoder.encode("Papayaland.123"), Sets.newHashSet(ApplicationUserRole.USER.name()));
//	me.setMe(userMe);
//	personRepository.save(me);
	
	
}
  
  
}