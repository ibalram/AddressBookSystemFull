package com.cg.addressbook.test;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.List;

import org.junit.Test;

import com.cg.addressbook.dto.Contact;
import com.cg.addressbook.service.AddressBookService;

public class AddressBookServiceTest {

	@Test
	public void givenAddressBookDBWhenRetrieved_ShouldMatchCount() {
		AddressBookService addressBookService = new AddressBookService();
		List<Contact> contactList = addressBookService.readAddressBookData();
		assertEquals(5, contactList.size());
	}

	@Test
	public void givenAddressBookDBWhenUpdated_ShouldSyncWithDB() {
		AddressBookService addressBookService = new AddressBookService();
		List<Contact> contactList = addressBookService.readAddressBookData();
		addressBookService.updatePhoneNumber("Balram", "91 9912345678");
		boolean result = addressBookService.checkAddressBookInSyncWithDB("Balram");
		assertTrue(result);
	}

	@Test
	public void givenAddressBookInDB_WhenRetrievedBasedOnCity_ShouldMatchPersonsCount() {
		AddressBookService addressBookService = new AddressBookService();
		List<Contact> contactList = addressBookService.readAddressBookData();
		LocalDate startDate = LocalDate.of(2020, 01, 01);
		LocalDate endDate = LocalDate.now();
		List<Contact> contactsAddedInDateRange = addressBookService.getContactsAddedInDateRange(startDate, endDate);
		assertEquals(5, contactsAddedInDateRange.size());
	}
}
