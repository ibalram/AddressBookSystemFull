package com.cg.addressbook.dto;

public class Contact {
	private String firstName;
	private String lastName;
	private String address;
	private String city;
	private String state;
	private String zip;
	private String phoneNumber;
	private String email;

	public Contact() {
	}

	public Contact(String firstName, String lastName, String address, String city, String state, String zip,
			String phoneNumber, String email) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.phoneNumber = phoneNumber;
		this.email = email;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAddress() {
		return address;
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

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getZip() {
		return zip;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public String getName() {
		return firstName + " " + lastName;
	}

	public String toString() {
		return String.format("Name: %s %s\nAddress: %s, %s, %s, %s\nPhone Number: %s\nEmail: %s", firstName, lastName,
				address, city, state, zip, phoneNumber, email);
	}
	
	public String getCommaSeparated() {
		return String.format("%s, %s, %s, %s, %s, %s, %s, %s", firstName, lastName, address, city, state, zip, phoneNumber, email);
	}
	
	public boolean equals(String name) {
		return this.getName().equalsIgnoreCase(name);
	}
}
