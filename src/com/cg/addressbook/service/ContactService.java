package com.cg.addressbook.service;

import java.util.Scanner;

import com.cg.addressbook.Validator;
import com.cg.addressbook.dto.AddressBook;
import com.cg.addressbook.dto.Contact;

public class ContactService {
	private Scanner sc;
	private AddressBook addressBook;
	private Validator validator;

	public ContactService(Scanner sc, AddressBook addressBook) {
		this.sc = sc;
		this.addressBook = addressBook;
		this.validator = new Validator();
	}

	public Contact createContact() {
		Contact contact = new Contact();
		String firstName, lastName, address, city, state, zip, email, phoneNumber;
		System.out.println("first name:");
		while (true) {
			try {
				firstName = sc.nextLine();
				validator.validateFirstName(firstName);
				contact.setFirstName(firstName);
				break;
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		System.out.println("last name:");
		while (true) {
			try {
				lastName = sc.nextLine();
				validator.validateLastName(lastName);
				contact.setLastName(lastName);
				break;
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		
		String name = firstName+" "+lastName;
		boolean duplicate = addressBook.isExistAlready(name);
		if (duplicate) {
			return null;
		}
		
		while (true) {
			try {
				address = sc.nextLine();
				validator.validateAddress(address);
				contact.setAddress(address);
				break;
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		System.out.println("city:");
		while (true) {
			try {
				city = sc.nextLine();
				validator.validateCity(city);
				contact.setCity(city);
				break;
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		System.out.println("state:");
		while (true) {
			try {
				state = sc.nextLine();
				validator.validateState(state);
				contact.setState(state);
				break;
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		System.out.println("zip:");
		while (true) {
			try {
				zip = sc.nextLine();
				validator.validateZip(zip);
				contact.setZip(zip);
				break;
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		System.out.println("Phone Number:");
		while (true) {
			try {
				phoneNumber = sc.nextLine();
				validator.validatePhoneNumber(phoneNumber);
				contact.setPhoneNumber(phoneNumber);
				break;
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		System.out.println("email:");
		while (true) {
			try {
				email = sc.nextLine();
				validator.validateEmail(email);
				contact.setEmail(email);
				break;
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		System.out.println("\nContact is added successfully\n");
		return contact;
	}

	public void editExistingContact(Contact contact) {
		while (true) {
			System.out.println("Options to update contact:");
			System.out.println(
					"1 (FirstName)\n2 (LastName)\n3 (Address)\n4 (City)\n5 (State)\n6 (Zip)\n7 (Phone)\n8 (Email)\n9 (Exit)");
			int options = Integer.parseInt(sc.nextLine());
			switch (options) {
			case 1:
				System.out.println("Enter New FirstName");
				String newFirstName = sc.nextLine();
				contact.setFirstName(newFirstName);
				break;
			case 2:
				System.out.println("Enter New LastName");
				String newLastName = sc.nextLine();
				contact.setLastName(newLastName);
				break;
			case 3:
				System.out.println("Enter New Address");
				String newAddress = sc.nextLine();
				contact.setAddress(newAddress);
				break;
			case 4:
				System.out.println("Enter New City");
				String newCity = sc.nextLine();
				contact.setCity(newCity);
				break;
			case 5:
				System.out.println("Enter State");
				String newState = sc.nextLine();
				contact.setState(newState);
				break;
			case 6:
				System.out.println("Enter New Zip");
				String newZip = sc.nextLine();
				contact.setZip(newZip);
				break;
			case 7:
				System.out.println("Enter New Phone");
				String newPhone = sc.nextLine();
				contact.setPhoneNumber(newPhone);
				break;
			case 8:
				System.out.println("Enter New Email");
				String newEmail = sc.nextLine();
				contact.setFirstName(newEmail);
				break;
			case 9:
				System.out.println("Exit");
				return;
			default:
				System.out.println("Invalid Entry");
				break;
			}
		}
	}

	public void displayContact(Contact contact) {
		if (contact == null) {
			System.out.println("Contact not found");
			return;
		}
		System.out.println(contact);
	}
}
