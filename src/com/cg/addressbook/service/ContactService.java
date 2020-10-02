package com.cg.addressbook.service;

import java.util.Scanner;

import com.cg.addressbook.Validator;
import com.cg.addressbook.dto.Contact;

public class ContactService {
	private Scanner sc;
	private Validator validator;
	
	public ContactService(Scanner sc ) {
		this.sc= sc;
		this.validator = new Validator();
	}
	
	public void createPerson(Contact contact, Scanner sc) {
		String firstName, lastName, address, city, state, zip, email, phoneNumber;
		System.out.println("first name:");
		while (true) {
			try {
				firstName = sc.nextLine();
				validator.validateFirstName(firstName);
				contact.setFirstName(firstName); break;
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		System.out.println("last name:");
		while (true) {
			try {
				lastName = sc.nextLine();
				validator.validateLastName(lastName);
				contact.setLastName(lastName); break;
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		System.out.println("address:");
		while (true) {
			try {
				address = sc.nextLine();
				validator.validateAddress(address);
				contact.setAddress(address); break;
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		System.out.println("city:");
		while (true) {
			try {
				city = sc.nextLine();
				validator.validateCity(city);
				contact.setCity(city); break;
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		System.out.println("state:");
		while (true) {
			try {
				state = sc.nextLine();
				validator.validateState(state);
				contact.setState(state); break;
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		System.out.println("zip:");
		while (true) {
			try {
				zip = sc.nextLine();
				validator.validateZip(zip);
				contact.setZip(zip); break;
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		System.out.println("Phone Number:");
		while (true) {
			try {
				phoneNumber = sc.nextLine();
				validator.validatePhoneNumber(phoneNumber);
				contact.setPhoneNumber(phoneNumber); break;
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		System.out.println("email:");
		while (true) {
			try {
				email = sc.nextLine();
				validator.validateEmail(email);
				contact.setEmail(email); break;
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		System.out.println("\nContact is added successfully\n");
	}
}
