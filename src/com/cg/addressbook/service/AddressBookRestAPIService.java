package com.cg.addressbook.service;

import java.util.Arrays;
import java.util.List;

import com.cg.addressbook.dto.Contact;
import com.google.gson.Gson;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class AddressBookRestAPIService {

	private void setup() {
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = 3000;
	}

	public AddressBookRestAPIService() {
		this.setup();
	}

	public Contact[] getContactList() {
		Response response = RestAssured.get("/contacts");
		// System.out.println(response.asString());
		Contact[] contacts = new Gson().fromJson(response.asString(), Contact[].class);
		return contacts;
	}

	public int addContactToJSONServer(Contact contact) {
		String contactJson = new Gson().toJson(contact);
		RequestSpecification request = RestAssured.given();
		request.header("Content-Type", "application/json");
		request.body(contactJson);
		return request.post("/contacts").getStatusCode();
	}

	public int updatePhoneNumber(String firstName, String phoneNumber) {
		List<Contact> contacts = Arrays.asList(getContactList());
		Contact contact = contacts.stream().filter(cont -> cont.getFirstName().equalsIgnoreCase(firstName)).findFirst()
				.orElse(null);
		if (contact == null)
			return 0;
		contact.setPhoneNumber(phoneNumber);
		return this.updateContactToJSONServer(contact);

	}

	private int updateContactToJSONServer(Contact contact) {
		String contactJson = new Gson().toJson(contact);
		RequestSpecification request = RestAssured.given();
		request.header("Content-Type", "application/json");
		request.body(contactJson);
		int statusCode = request.put("/contacts/" + contact.getFirstName()).getStatusCode();
		return statusCode == 200 ? statusCode : 0;
	}

	public Contact getAddressBookData(String name) {
		return Arrays.asList(getContactList()).stream().filter(contact -> contact.getFirstName().equalsIgnoreCase(name))
				.findFirst().orElse(null);
	}

}
