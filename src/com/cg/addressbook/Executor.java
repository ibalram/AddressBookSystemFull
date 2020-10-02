package com.cg.addressbook;

import java.util.Scanner;

import com.cg.addressbook.dto.AddressBook;
import com.cg.addressbook.dto.AddressBooks;
import com.cg.addressbook.service.AddressBookService;

public class Executor {

	public static void addNewAddressBook(AddressBooks addressBooks, AddressBookService addressBookService, Scanner sc) {
		System.out.print("Enter Name:");
		String name;
		name = sc.nextLine();
		if (addressBooks.searchAddressBook(name) != null) {
			System.out.println("Already exist");
			return;
		}
		AddressBook addressBook = new AddressBook(name);
		addressBooks.addAddressBook(addressBook);
		System.out.print("Added new Address Book\n");
	}

	public static void viewExistingAddressBook(AddressBooks addressBooks, AddressBookService addressBookService,
			Scanner sc) {
		System.out.print("Enter Name:");
		String name = sc.nextLine();
		AddressBook addressBook = addressBooks.searchAddressBook(name);
		if (addressBook != null) {
			addressBookService.addressBookOptions(addressBook);
			return;
		}
		System.out.print("No addres book found\n");
	}

	public static void main(String[] args) {
		System.out.println("Welcome to Address Book Program");
		System.out.println("--------------------------------");
		Scanner sc = new Scanner(System.in);
		AddressBooks addressBooks = new AddressBooks();
		AddressBookService addressBookService = new AddressBookService(sc);

		boolean repeat = true;
		while (repeat) {
			System.out
					.println("Main Options:\n1 (Add new AddressBook)\n2 (View or open existing AddressBook)\n3 (Exit)");
			int option = Integer.parseInt(sc.nextLine());
			switch (option) {
			case 1:
				addNewAddressBook(addressBooks, addressBookService, sc);
				break;
			case 2:
				viewExistingAddressBook(addressBooks, addressBookService, sc);
				break;
			case 3:
				repeat = false;
				break;
			default:
				System.out.println("Invalid Entry");
			}
		}
	}
}
