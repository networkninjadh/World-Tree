   package com.howtech;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.howtech.models.FamilyTree;
import com.howtech.models.Person;
import com.howtech.repositories.FamilyTreeRepository;
import com.howtech.repositories.PersonRepository;

@EntityScan(basePackages = "com.howtech.models")
@EnableTransactionManagement
@EnableNeo4jRepositories
@SpringBootApplication 
public class AccessingDataNeo4jApplication implements CommandLineRunner{

	@Autowired
	private PersonRepository personRepository;
	
	@Autowired
	private FamilyTreeRepository familyTreeRepository;
	
  public static void main(String[] args) {
    SpringApplication.run(AccessingDataNeo4jApplication.class, args);
  }

@Override
public void run(String... args) throws Exception {
	Person me = new Person();
	Person uncle  = new Person("Uncle", "Uncle", "Uncle");

	//create grandfather1
	Person grandFather = new Person("grand-father-1", "Dads", "Grand Father 1");
	grandFather.setChild(new Person("Uncle", "OR", "Aunt"));
	grandFather.setChild(uncle);
	//create grandmother1
	Person grandMother = new Person("grand-mother-1", "Dads", "Grand Mother 1");
	grandMother.setChild(uncle);
	//create grandmother2
	Person grandMother2 = new Person("grand-mother-2", "Mothers", "Grand Mother 2");
	//create grandfather2
	Person grandFather2 = new Person("grand-father-2", "Mothers", "Grand Father 2");
	grandFather.setChild(new Person("Uncle", "OR", "AUNT"));
	grandFather.setChild(new Person("Uncle", "OR", "AUNT"));
		//add another child
	//create mother
	Person mother = new Person("My Mother", "Mother", "My Mother");
	mother.setFather(grandFather2);
	mother.setMother(grandMother2);
	mother.setChild(new Person("Mothers", "First", "Mother's Child"));
		//add another child
	//create father
	Person father = new Person("Father", "Father", "Father");
	father.setFather(grandFather);
	father.setMother(grandMother);
	//create brother
	father.setChild(new Person("Fathers", "First", "Father's Child"));
	father.setChild(new Person("Fathers", "Second", "Father's Child"));
	//create child
	me.setMother(mother);
	me.setFather(father);
	
	Person wife = new Person("Wife", "", "Wife");
	Person baby = new Person("MY Baby", "", "Baby");
	baby.setMother(wife);
	baby.setFather(me);
	wife.setChild(baby);
	me.setChild(baby);
	
	personRepository.save(me);
}
  
  
}