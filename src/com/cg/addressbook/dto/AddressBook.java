package com.cg.addressbook.dto;

import java.util.ArrayList;

public class AddressBook {
	private ArrayList<Contact> addressList = new ArrayList<Contact>();

	public void setAddressList(ArrayList<Contact> addressList) {
		this.addressList = addressList;
	}

	public ArrayList<Contact> getAddressList() {
		return addressList;
	}

	public void addContact(Contact contact) {
		addressList.add(contact);
	}

	public Contact searchByName(String name) {
		for (int i = 0; i < addressList.size(); ++i) {
			if (addressList.get(i).getName().contains(name))
				return addressList.get(i);
		}
		return null;
	}

	public boolean deleteContact(String name) {
		for (int i = 0; i < addressList.size(); ++i) {
			if (addressList.get(i).getName().equalsIgnoreCase(name)) {
				addressList.remove(i);
				return true;
			}
		}
		return false;
	}

	public String toString() {
		StringBuilder str = new StringBuilder();
		int count = 1;
		for (Contact contact : addressList) {
			if (contact != null) {
				str.append(count + ". " + contact.getFirstName() + " " + contact.getLastName() + "\n");
				count++;
			}
		}
		if (str.length() == 0)
			return "\nNo contact found";
		return str.toString();
	}
}
