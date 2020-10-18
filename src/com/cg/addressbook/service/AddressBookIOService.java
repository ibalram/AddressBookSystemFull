package com.cg.addressbook.service;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.cg.addressbook.dto.Contact;

public class AddressBookIOService {
	public static String CONTACT_FILE_NAME = "./contacts.txt";

	public AddressBookIOService() {
	}

	public List<Contact> readData() {
		List<Contact> contactList = new ArrayList<Contact>();
		try {
			Files.lines(new File(CONTACT_FILE_NAME).toPath()).map(line -> line.trim()).forEach(line -> {
				String[] data = line.split(",");
				Contact contact = new Contact(data[0], data[1], data[2], data[3], data[4], data[5], data[6], data[7]);
				contactList.add(contact);
			});
		} catch (Exception e) {
			System.out.println("Exception occured: " + e);
		}
		return contactList;
	}

	public void writeData(List<Contact> contactList) {
		StringBuilder str = new StringBuilder();
		contactList.forEach(contact -> {
			str.append(contact.getCommaSeparated() + "\n");
		});
		try {
			Files.write(Paths.get(CONTACT_FILE_NAME), str.toString().getBytes());
		} catch (Exception e) {
			System.out.println("Exception occured: " + e);
		}
	}
}
