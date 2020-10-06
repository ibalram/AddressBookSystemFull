package com.cg.addressbook.dto;

import java.util.ArrayList;
import java.util.Comparator;

public class AddressBook {
	private ArrayList<Contact> addressList = new ArrayList<Contact>();
	private String name;
	
	public AddressBook(String name) {
		this.name = name;
	}

	public void setAddressList(ArrayList<Contact> addressList) {
		this.addressList = addressList;
	}

	public ArrayList<Contact> getAddressList() {
		return addressList;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
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
	
	public ArrayList<Contact> searchByCity(String city) {
		ArrayList<Contact> list = new ArrayList<Contact>();
		for (int i = 0; i < addressList.size(); ++i) {
			if (addressList.get(i).getCity().equalsIgnoreCase(city))
				list.add(addressList.get(i));
		}
		return list;
	}
	
	public ArrayList<Contact> searchByState(String state) {
		ArrayList<Contact> list = new ArrayList<Contact>();
		for (int i = 0; i < addressList.size(); ++i) {
			if (addressList.get(i).getState().equalsIgnoreCase(state))
				list.add(addressList.get(i));
		}
		return list;
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
	
	public boolean isExistAlready(String name) {
		int count = (int) addressList.stream().filter(contact->contact.equals(name)).count();
		System.out.println("address:");
		return count!=0;
	}
	
	public Object[] viewContactsSortedByName() {
		return addressList.stream().sorted(Comparator.comparing(contact->contact.getName())).toArray();
	}
}
