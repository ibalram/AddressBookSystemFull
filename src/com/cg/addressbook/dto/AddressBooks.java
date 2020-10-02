package com.cg.addressbook.dto;

import java.util.HashMap;

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
}
