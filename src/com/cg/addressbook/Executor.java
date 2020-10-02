package com.cg.addressbook;

import java.util.Scanner;

import com.cg.addressbook.dto.AddressBook;
import com.cg.addressbook.dto.Contact;
import com.cg.addressbook.service.ContactService;

public class Executor {
	private static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		System.out.println("Welcome to Address Book Program");
		System.out.println("--------------------------------");
		AddressBook addressBook = new AddressBook();
		ContactService contactService = new ContactService(sc);
		Contact contact = new Contact();
		contactService.createPerson(contact, sc);
		System.out.println(contact+"\n");
		addressBook.addContact(contact);
		System.out.println("Contact is added to addressBook");
		
	}
}
