package org.gurinder.project.Entity;

public class User {

	public String surname;
	
	public String age;		
	
	public String city;

	public User(String surname, String age, String city) {
		super();
		this.surname = surname;
		this.age = age;
		this.city = city;
	}

	public User() {
		
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String toString()
	{
		return ("UserName:"+this.getSurname()+" Age:"+this.getAge()+" City:"+this.getCity());
	}
	
	
	
}
