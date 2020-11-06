package com.cg.addressbook.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import com.cg.addressbook.dto.Contact;
import com.cg.addressbook.service.AddressBookService;
import com.google.gson.Gson;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class AddressBookRestAPITest {
	@Before
	public void setup() {
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = 3000;
	}

	private Contact[] getContactList() {
		Response response = RestAssured.get("/contacts");
		// System.out.println(response.asString());
		Contact[] contacts = new Gson().fromJson(response.asString(), Contact[].class);
		return contacts;
	}

	@Test
	public void givenContactsOnJsonServerWhenRetrieved_ShouldMatchCount() {
		Contact[] contacts = getContactList();
		AddressBookService service = new AddressBookService(Arrays.asList(contacts));
		int count = service.countEntries();
		assertEquals(3, count);
	}

	private Response addContactToJSONServer(Contact contact) {
		String contactJson = new Gson().toJson(contact);
		RequestSpecification request = RestAssured.given();
		request.header("Content-Type", "application/json");
		request.body(contactJson);
		return request.post("/contacts");
	}

	@Test
	public void givenNewContactWhenAdded_ShouldReturn201ResponseAndCount() {
		Contact[] contacts = getContactList();
		AddressBookService service = new AddressBookService(Arrays.asList(contacts));
		Contact contact = new Contact("Askhit", "Singh", "no location", "Jaipur", "Rajasthan", "301234",
				"91 9812345678", "akshit@gmail.com");
		Response response = addContactToJSONServer(contact);
		int statusCode = response.getStatusCode();
		assertEquals(201, statusCode);

		contacts = getContactList();
		service = new AddressBookService(Arrays.asList(contacts));
		int count = service.countEntries();
		assertEquals(4, count);
	}
}
