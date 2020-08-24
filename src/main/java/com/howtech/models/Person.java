package com.howtech.models;

import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Calendar;


import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Set;
import java.util.Stack;

@NodeEntity(label="FamilyMember")
public class Person implements Comparable<Person>{
	@Id
	@GeneratedValue
	private Long id;
	private boolean active;
	private boolean initialized = false;
	private String firstName;
	private String middleName;
	private String lastName;
	private Date birthdate;
	private Date deathdate = null;
	private int age;
	private Gender gender;
	
	@Relationship(type = "USER_ACCOUNT", direction = Relationship.OUTGOING)
	private User me;
	//Mother and Father person objects everyone has at least theses two
	//@JsonIgnore
	@Relationship(type = "MOTHER", direction = Relationship.OUTGOING)
	private Person mother;
	//@JsonIgnore
	@Relationship(type = "FATHER", direction = Relationship.OUTGOING)
	private Person father;
	
	@JsonIgnore
	@Relationship(type = "CHILD", direction = Relationship.OUTGOING)
	private Set<Person> children;
		
	private String country;
	private String state;
	private String city;
	private String street;
	private int streetNumber;
	private int aptNumber;
	private String phoneNumber;
	private String email;
	
	public Person(String lastName) {
		this.lastName = lastName;
		mother = null;
		father = null;
		children = new HashSet<>();
	}
	
	public Person(String firstName, String middleName, String lastName, Gender gender) {
		this.middleName = middleName;
		this.lastName = lastName;
		this.firstName = firstName;
		this.gender = gender;
		mother = null;
		father = null;
		children = new HashSet<>();
	}
	
	public Person() {
		mother = null;
		father = null;
		
		children = new HashSet<>();
	}
	
	public Long getId() {
		return this.id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	
	
	public boolean isInitialized() {
		return initialized;
	}

	public void setInitialized(boolean initialized) {
		this.initialized = initialized;
	}

	public Date getDeathdate() {
		return deathdate;
	}

	public void setDeathdate(Date deathdate) {
		this.deathdate = deathdate;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public User getMe() {
		return me;
	}

	public void setMe(User me) {
		this.me = me;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public void setChildren(Set<Person> children) {
		this.children = children;
	}

	public boolean isActive() {
		return active;
	}
	
	public void setMother(Person mother) {
		this.mother = mother;
		mother.children.add(this);
	}
	public void setMother(Person mother, Stack<Person> stack) {
		if (!stack.contains(this)) {
			stack.add(this);
			this.mother = mother;
			mother.setChild(this, stack);
		}
	}
	public Person getMother() {
		return mother;
	}
	
	public void setFather(Person father) {
		this.father = father;
		father.children.add(this);
	}
	public void setFather(Person father, Stack<Person> stack) {
		if (!stack.contains(this)) {
			stack.add(this);
			this.father = father;
			father.setChild(this, stack);
		}
	}
	public Person getFather() {
		return father;
	}
	
	public void setChild(Person child) {
		this.children.add(child);
		if (this.gender == Gender.MALE) {
			child.father = this;
		}else if (this.gender == Gender.FEMALE) {
			child.mother = this;
		}
	}
	
	public void setChild(Person child, Stack<Person> stack) {
		if (this.gender == Gender.MALE) {
			if (!stack.contains(this)) {
				stack.add(this);
				children.add(child);
				child.setFather(this, stack);
			}
			
		}else if (this.gender == Gender.FEMALE) {
			if (!stack.contains(this)) {
				stack.add(this);
				children.add(child);
				child.setMother(this, stack);
			}
		}
	}

	public Person getChild(int i) {
		Object[] array = children.toArray();
		return (Person) array[i];
	}
	public Set<Person> getChildren() {
		return (HashSet<Person>) children;
	}
	
	//Setters and getters for personal information
	public void setBirthdate(int year, int month, int day) {
		Calendar cal = Calendar.getInstance();
		cal.set(year, day, month);
		birthdate = cal.getTime();
	}
	
	public Date getBirthdate() {
		return birthdate;
	}
	
	public void setDeathDate(int year, int month, int day) {
		Calendar cal = Calendar.getInstance();
		cal.set(year, day, month);
		deathdate = cal.getTime();
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	
	public String getMiddleName() {
		return middleName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	private String getAddressInfo() {
		return (streetNumber + street + aptNumber + "\n"
				+ country + "\n"
				+ state + "\n"
				+ city + "\n");
	}
	
	//Setters and getters for address information
	public void setStreetNumber(int streetNumber) {
		this.streetNumber = streetNumber;
	}
	
	public int getStreetNumber() {
		return streetNumber;
	}
	
	public void setStreet(String street) {
		this.street = street;
	}
	
	public String getStreet() {
		return street;
	}
	
	public void setAptNumber(int aptNumber) {
		this.aptNumber = aptNumber;
	}
	
	public int getAptNumber() {
		return aptNumber;
	}
	
	public void setCity(String city) {
		this.city = city;
	}
	
	public String getCity() {
		return city;
	}
	
	public void setState(String state) {
		this.state = state;
	}
	
	public String getState() {
		return state;
	}
	
	public void setCountry(String country) {
		this.country = country;
	}
	
	public String getCountry() {
		return country;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}

	//Helper functions
	private String getContactInfo() {
		return ("Phone : " + phoneNumber + "\n"
				+ "Email : " + email);
	}
	
	private String getChildrenInfo() {
		String retString = "";
		Iterator<Person> it = children.iterator();
		while(it.hasNext()) {
			if (it.next().initialized)
			retString += it.next().toString(); //if it is being printed as a child don't print parents
		}
			return retString;
	}
	
	@Override
	public int compareTo(Person person) {
		if (person.getId() == this.getId()) {
			return 0;
		}else if (person.getId() < this.getId()) {
			return -1;
		}else {
			return 1;
		}
	}
	
	@Override 
	public String toString() {
		String retString = "";
		if (mother == null && father != null) {
			retString = "Father : " + father.toString() + "Mother : Unknown\n";
		}else if (mother == null && father == null) {
			retString = "Father : Unkown Mother : Unkown\n";
		}else if (mother != null && father == null) {
			retString = "Father : Unknown Mother : " + mother.toString() + "\n";
		}else if (mother != null && father != null) {
			retString = "Father : " + father.toString() + " Mother : " + mother.toString() + "\n";
		}
		retString += 
				getFirstName() + " " + getMiddleName() + " " + getLastName() + "\n"
				+ "lived " + birthdate + " to " + deathdate + "\n"
				+ age + " years old " + "and lives at \n"
				+ getAddressInfo() + "\n"
				+ getContactInfo() + "\n"
				+ "Children : \n" 
				+ getChildrenInfo();
		return retString;
	}
}