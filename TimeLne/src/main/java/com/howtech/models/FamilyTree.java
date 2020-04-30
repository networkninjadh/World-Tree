package com.howtech.models;


import java.util.Set;


public class FamilyTree {
	
	//@Id
	//@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private Person root;
	int size;
	
	String data;
	
	FamilyTree(){
		this.root = null;
		this.size = 0;
	}
	FamilyTree(Person me){
		root = me;
		size++;
		data = this.toString();
	}
	Person root() {
		return root;
	}
	boolean isEmpty() {
		if (root == null){
			return false;
		}else
			return true;
	}
	public void setMother(Person mother) {
		if(this.root != null) {
			this.root.setMother(mother);
		}else {
			System.out.println("Cannot add mother to empty tree");
		}
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Person getRoot() {
		return root;
	}
	public void setRoot(Person root) {
		this.root = root;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public Person getMother() {
		return root.getMother();
	}
	public void setFather(Person father) {
		if(this.root != null) {
			this.root.setFather(father);
		}else {
			System.out.println("Cannot add a father to an empty tree");
		}
	}
	public Person getFather(Person father) {
		return root.getFather();
	}
	//sets the next child to the person 
	public void setChild(Person child) {
		root.setChild(child);
	}
	public Person getChild(int i) {
		return root.getChild(i);
	}
	public Set<Person> getChildren(){
		return (Set<Person>) root.getChildren();
	}
	
	@Override
	public String toString() { //Person should only print a person this method should handle printing all elements in the tree
		return root.toString();
	} 
}
