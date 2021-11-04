package com.kokabmedia.jdbc.entity;

import java.util.Date;
/*
 * This is a model class for mapping and updating data in a database.
 * 
 * Row Mapping of this class will be done with the JdbcTemplate class.
 */
public class Person {
	private int id;
	private String name;
	private String location;
	private Date birthDate;
	
	public Person() {}
	
	public Person(int id, String name, String location, Date birthDate) {
		super();
		this.id = id;
		this.name = name;
		this.location = location;
		this.birthDate = birthDate;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}


	/*
	 * The purpose of this method is to returns a textual representation 
	 * of the object, instead of for example hash code in the logger.
	 */
	@Override
	public String toString() {
		return "\nPerson [id=" + id + ", name=" + name + ", location=" + location + ", birthDate=" + birthDate + "]";
	}
	
	
	

}
