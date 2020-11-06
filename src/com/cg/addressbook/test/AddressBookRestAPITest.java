package com.cg.addressbook.test;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.cg.addressbook.dto.Contact;
import com.cg.addressbook.service.AddressBookService;
import com.cg.addressbook.service.AddressBookService.IOService;

public class AddressBookRestAPITest {
	AddressBookService addressBookService;

	@Before
	public void setup() {
		addressBookService = new AddressBookService();
	}

	@Test
	public void givenContactsOnJsonServerWhenRetrieved_ShouldMatchCount() {
		List<Contact> contactList = addressBookService.readAddressBookData(IOService.REST_IO);
		int count = addressBookService.countEntries();
		assertEquals(3, count);
	}

	@Test
	public void givenNewContactWhenAdded_ShouldReturn201ResponseAndCount() {
		List<Contact> contactList = addressBookService.readAddressBookData(IOService.REST_IO);
		Contact contact = new Contact("Askhit", "Singh", "no location", "Jaipur", "Rajasthan", "301234",
				"91 9812345678", "akshit@gmail.com");
		int statusCode = addressBookService.addContactToJSONServer(contact);
		assertEquals(201, statusCode);

		int count = addressBookService.countEntries();
		assertEquals(4, count);
	}

	@Test
	public void givenMultipleContactsWhenAdded_ShouldReturn201ResponseAndCount() {
		List<Contact> contactList = addressBookService.readAddressBookData(IOService.REST_IO);
		Contact[] contacts = {
				new Contact("Terisa", "William", "no location", "Mumbai", "MH", "401234", "91 9812345678",
						"Terisa@gmail.com"),
				new Contact("Mark", "Biden", "no location", "Chennai", "Karnataka", "501234", "91 9812123678",
						"Mark@gmail.com") };
		addressBookService.addMultipleContacts(Arrays.asList(contacts), IOService.REST_IO);

		int count = addressBookService.countEntries();
		assertEquals(6, count);
	}

	@Test
	public void givenAddressBookOnServerWhenUpdated_ShouldBeInSyncWithMemory() {
		List<Contact> contactList = addressBookService.readAddressBookData(IOService.REST_IO);
		addressBookService.updatePhoneNumber("Balram", "91 8949759544", IOService.REST_IO);
		boolean result = addressBookService.checkAddressBookInSync("Balram", IOService.REST_IO);
		assertTrue(result);
	}
}
