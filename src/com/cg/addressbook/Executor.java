package com.cg.addressbook;

import java.util.Scanner;

import com.cg.addressbook.dto.Contact;
import com.cg.addressbook.service.ContactService;

public class Executor {
	private static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		System.out.println("Welcome to Address Book Program");
		System.out.println("--------------------------------");
		ContactService contactService = new ContactService(sc);
		Contact contact = new Contact();
		contactService.createPerson(contact, sc);
		System.out.println(contact);
	}
}
