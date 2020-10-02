package com.cg.addressbook.dto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AddressBooks {
	private HashMap<String, AddressBook> addressBooks;

	public AddressBooks() {
		this.addressBooks = new HashMap<String, AddressBook>();
	}
	
	public void addAddressBook(AddressBook addressBook) {
		addressBooks.put(addressBook.getName(), addressBook);
	}
	
	public AddressBook searchAddressBook(String name) {
		for (String key: addressBooks.keySet()) {
			if (key.equalsIgnoreCase(name)) {
				return addressBooks.get(key);
			}
		}
		return null;
	}
	
	public List<Contact> searchByCity(String city) {
		List<Contact> list = new ArrayList<Contact>();
		for (Map.Entry entry: addressBooks.entrySet()) {
			List<Contact> contactList = ((AddressBook)entry.getValue()).searchByCity(city);
			for (Contact contact: contactList) {
				list.add(contact);
			}
		}
		return list.stream().filter(contact->contact!= null).distinct().collect(Collectors.toList());
	}
	
	public List<Contact> searchByState(String state) {
		List<Contact> list = new ArrayList<Contact>();
		for (Map.Entry entry: addressBooks.entrySet()) {
			List<Contact> contactList = ((AddressBook)entry.getValue()).searchByState(state);
			for (Contact contact: contactList) {
				list.add(contact);
			}
		}
		return list.stream().filter(contact->contact!= null).distinct().collect(Collectors.toList());
	}
}
