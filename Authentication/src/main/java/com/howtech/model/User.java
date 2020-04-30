package com.howtech.model;

import java.util.List;
import java.util.Set;

import javax.validation.constraints.Size;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

@NodeEntity
public class User {

	@Id
	@GeneratedValue
	private Long id;
	private boolean active;
	
	private String firstName;
	private String middleName;
	private String lastName;
	
	@Size(min = 4, max = 255, message = "Minimum username length 4 chars")
	private String username;
	
	@Size(min=8, max = 255, message = "Minimum password length 8 chars")
	private String password;
	
	@Size(min= 8, max = 255, message = "Minimum email length 8 chars")
	private String email;
	
	@Size(min=8, max = 255, message = "Minimum password length is 8 chars")
	List<Role> roles;
	
	public String getUsername() {
		return username; 
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	private int age;
	private String gender;
	
	@Relationship(type = "MOTHER", direction = Relationship.INCOMING)
	private User mother;
	
	@Relationship(type = "FATHER", direction = Relationship.INCOMING)
	private User father;
	
	@Relationship(type = "CHILDREN", direction = Relationship.OUTGOING)
	private Set<User> children;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public User getMother() {
		return mother;
	}

	public void setMother(User mother) {
		this.mother = mother;
	}

	public User getFather() {
		return father;
	}

	public void setFather(User father) {
		this.father = father;
	}

	public Set<User> getChildren() {
		return children;
	}

	public void setChildren(Set<User> children) {
		this.children = children;
	}

	public void setEmail(String email) {
		this.email = email;
		
	}
	
	public String getEmail() {
		return email;
	}
	
}
