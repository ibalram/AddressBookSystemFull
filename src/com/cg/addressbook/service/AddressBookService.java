package com.cg.addressbook.service;

import java.util.Objects;
import java.util.Scanner;

import com.cg.addressbook.Validator;
import com.cg.addressbook.dto.AddressBook;
import com.cg.addressbook.dto.Contact;

public class AddressBookService {
	private Scanner sc;
	private Validator validator;
	private ContactService contactService;
	private AddressBook addressBook;

	public AddressBookService(Scanner sc) {
		this.sc = sc;
		this.validator = new Validator();
	}

	public void findContact() {
		System.out.println("Enter Person Name");
		String name = sc.nextLine();
		contactService.displayContact(addressBook.searchByName(name));
	}

	public void editContact() {
		System.out.println("Enter Person Name");
		String name = sc.nextLine();
		Contact contact = addressBook.searchByName(name);
		if (Objects.nonNull(contact)) {
			contactService.editExistingContact(contact);
			return;
		}
		System.out.println("Person Not Found\n");
	}

	public void deleteContact() {
		System.out.println("Enter Person Name");
		String name = sc.nextLine();
		if (Objects.nonNull(addressBook.searchByName(name))) {
			addressBook.deleteContact(name);
			return;
		}
		System.out.println("Person Not Found\n");
	}

	public void createContact() {
		Contact contact = contactService.createContact();
		if (contact==null) {
			System.out.println("Already exist in Address Book\n");
			return;
		}
		addressBook.addContact(contact);
		System.out.println("\nContact is added successfully\n");
	}
	
	public void viewContactsSortedByName() {
		System.out.println("All Contacts in AddressBook (sorted by Name): ");
		Object[] obj = addressBook.viewContactsSortedByName();
		int count = 1;
		for (Object contact: obj) {
			System.out.println(count+"\n"+contact+"\n");
			count++;
		}
		if(count==1)
			System.out.println("No Contact Found\n");
	}

	public void addressBookOptions(AddressBook addressBook) {
		this.addressBook = addressBook;
		contactService = new ContactService(sc, addressBook);
		boolean repeat = true;
		while (repeat) {
			System.out.println(
					"\nAddress Book Options:\n1 (Add new contact)\n2 (Edit existing contact)\n3 (Delete a contact)\n4 (View all contacts sorted by Name)\n5 (Display contact)\n6 Exit");
			int option = Integer.parseInt(sc.nextLine());
			switch (option) {
			case 1:
				createContact();
				break;
			case 2:
				editContact();
				break;
			case 3:
				deleteContact();
				break;
			case 4:
				//System.out.println(addressBook);
				viewContactsSortedByName();
				break;
			case 5:
				findContact();
				break;
			case 6:
				repeat = false;
				System.out.println("Exit\n");
				break;
			default:
				System.out.println("Invalid Entry\n");
			}
		}
	}
}
