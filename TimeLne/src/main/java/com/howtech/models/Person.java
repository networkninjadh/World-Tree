package com.howtech.models;


import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Calendar;


import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.Set;


@NodeEntity(label="FamilyMember")
public class Person {
	//Id in the database
	@Id
	@GeneratedValue
	private Long id;
	//private boolean active;
	
//	private boolean initialized = false;
	//Name information
	private String firstName;
	private String middleName;
	private String lastName;
	
	//Birth and death dates
	//private Date birthdate;
	//private Date deathdate = null;
	//private int age;
	//private String gender;
	
	
	//Mother and Father person objects everyone has at least theses two
	@Relationship(type = "MOTHER", direction = Relationship.INCOMING)
	private Person mother;
	@Relationship(type = "FATHER", direction = Relationship.INCOMING)
	private Person father;
	
	//list of children
	@Relationship(type = "CHILDREN", direction = Relationship.OUTGOING)
	private Set<Person> children;
	
	//Address information
	/*
	private String country;
	
	private String state;
	
	private String city;
	
	private String street;
	
	private int streetNumber;
	
	private int aptNumber;
	
	//contact info
	
	private String phoneNumber;
	
	private String email;
	*/
	
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
	/*
	public boolean isActive() {
		return active;
	}*/
	public void setMother(Person mother) {
		this.mother = mother;
	}
	public Person getMother() {
		return mother;
	}
	public void setFather(Person father) {
		this.father = father;
	}
	public Person getFather() {
		return father;
	}
	public void setChild(Person child) {
		children.add(child);
	}
	public Person getChild(int i) {
		Object[] array = children.toArray();
		return (Person) array[i];
	}
	public Set<Person> getChildren() {
		return (HashSet<Person>) children;
	}
	/*
	//Setters and getters for personal information
	public void setBirthdate(int year, int month, int day) {
		Calendar cal = Calendar.getInstance();
		cal.set(year,  day, month);
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
	*/
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
	/*
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
	// aptNumber city state country
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
	*/
	/*
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
	*/
}
