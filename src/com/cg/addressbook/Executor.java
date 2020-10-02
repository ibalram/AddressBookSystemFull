package com.cg.addressbook;

import java.util.Objects;
import java.util.Scanner;

import com.cg.addressbook.dto.AddressBook;
import com.cg.addressbook.dto.Contact;
import com.cg.addressbook.service.ContactService;

public class Executor {
	private static Scanner sc = new Scanner(System.in);
	static ContactService contactService;
	static AddressBook addressBook;

	public static void findContact() {
		System.out.println("Enter Person Name");
		String name = sc.nextLine();
		contactService.displayContact(addressBook.searchByName(name));
	}

	public static void editContact() {
		System.out.println("Enter Person Name");
		String name = sc.nextLine();
		Contact contact = addressBook.searchByName(name);
		if (Objects.nonNull(contact)) {
			contactService.editExistingContact(contact);
			return;
		}
		System.out.println("Person Not Found");
	}

	public static void deleteContact() {
		System.out.println("Enter Person Name");
		String name = sc.nextLine();
		if (Objects.nonNull(addressBook.searchByName(name))) {
			addressBook.deleteContact(name);
			return;
		}
		System.out.println("Person Not Found");
	}

	public static void createContact() {
		addressBook.addContact(contactService.createContact());
	}

	public static void main(String[] args) {
		System.out.println("Welcome to Address Book Program");
		System.out.println("--------------------------------");
		addressBook = new AddressBook();
		contactService = new ContactService(sc);

		boolean repeat = true;
		while (repeat) {
			System.out.println(
					"\nAddress Book Options:\n1 (Add new contact)\n2 (Edit existing contact)\n3 (Delete a contact)\n4 (View all contact)\n5 (Display contact)\n6 Exit");
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
				System.out.println(addressBook);
				break;
			case 5:
				findContact();
				break;
			case 6:
				repeat = false;
				System.out.println("Exit");
				break;
			default:
				System.out.println("Invalid Entry");
			}
		}
	}
}
