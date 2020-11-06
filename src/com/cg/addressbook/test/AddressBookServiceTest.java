package com.cg.addressbook.test;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.cg.addressbook.dto.Contact;
import com.cg.addressbook.service.AddressBookService;
import com.cg.addressbook.service.AddressBookService.IOService;

public class AddressBookServiceTest {
	private AddressBookService addressBookService;

	@Before
	public void initialise() {
		addressBookService = new AddressBookService();
	}

	@Test
	public void givenAddressBookDBWhenRetrieved_ShouldMatchCount() {
		List<Contact> contactList = addressBookService.readAddressBookData(IOService.DB_IO);
		assertEquals(5, contactList.size());
	}

	@Test
	public void givenAddressBookDBWhenUpdated_ShouldSyncWithDB() {
		addressBookService.readAddressBookData(IOService.DB_IO);
		addressBookService.updatePhoneNumber("Balram", "91 9912345678", IOService.DB_IO);
		boolean result = addressBookService.checkAddressBookInSync("Balram", IOService.DB_IO);
		assertTrue(result);
	}

	@Test
	public void givenAddressBookDBWhenRetrievedForDateRange_ShouldMatchCount() {
		addressBookService.readAddressBookData(IOService.DB_IO);
		LocalDate startDate = LocalDate.of(2020, 01, 01);
		LocalDate endDate = LocalDate.now();
		List<Contact> contactsAddedInDateRange = addressBookService.getContactsAddedInDateRange(IOService.DB_IO,
				startDate, endDate);
		assertEquals(5, contactsAddedInDateRange.size());
	}

	@Test
	public void givenAddressBookDBWhenRetrievedForCityOrState_ShouldMatchCount() {
		addressBookService.readAddressBookData(IOService.DB_IO);
		List<Contact> contactsByCityOrState = addressBookService.getContactsByCityOrState(IOService.DB_IO, "Jaipur",
				"Delhi");
		assertEquals(3, contactsByCityOrState.size());
	}

	@Test
	public void givenAddressBookDBWhenAddedContactToDB_shouldMatchCount() {
		addressBookService.readAddressBookData(IOService.DB_IO);
		addressBookService.addContactToAddressBookDB("Mark", "Wood", "no address", "Mumbai", "Maharashtra", "412345",
				"91 9812345678", "mark@gmail.com", LocalDate.now());
		boolean result = addressBookService.checkAddressBookInSync("Mark", IOService.DB_IO);
		assertTrue(result);
	}

	@Test
	public void givenAddressBookDBWhenMultipleContactsAddedToDB_shouldMatchCount() {
		addressBookService.readAddressBookData(IOService.DB_IO);
		List<Contact> contactList = Arrays.asList(
				new Contact("William", "Lin", "no address", "Chennai", "Karnataka", "123345", "91 9535352378",
						"tmwilliam@gmail.com", LocalDate.now()),
				new Contact("Alex", "Wice", "no address", "Chennai", "Karnataka", "123345", "91 9535352378",
						"awice@gmail.com", LocalDate.now()),
				new Contact("Terisa", "Wilson", "no address", "Mumbai", "Maharashtra", "432345", "91 9997652378",
						"terisa@gmail.com", LocalDate.now()));
		addressBookService.addMultipleContacts(contactList, IOService.DB_IO);
		contactList = addressBookService.readAddressBookData(IOService.DB_IO);
		assertEquals(9, contactList.size());
	}
}
