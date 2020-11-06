package com.cg.addressbook.service;

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

}
