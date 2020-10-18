package com.cg.addressbook.service;

import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import com.cg.addressbook.dto.Contact;
import com.google.gson.*;

public class AddressBookJSONService {
	private String ADDRESSBOOK_FILE_NAME = "./contactsJson.json";

	public List<Contact> readData() {
		List<Contact> contactList = null;
		try {
			Gson gson = new Gson();
			Reader reader = Files.newBufferedReader(Paths.get(ADDRESSBOOK_FILE_NAME));
			Contact[] contacts = gson.fromJson(reader, Contact[].class);
			contactList = Arrays.asList(contacts);
		} catch (Exception e) {
			System.out.println("Exception occurred: " + e);
		}
		return contactList;
	}

	public void writeData(List<Contact> contactList) {
		try {
			Gson gson = new Gson();
			String json = gson.toJson(contactList);
			Writer writer = Files.newBufferedWriter(Paths.get(ADDRESSBOOK_FILE_NAME));
			writer.write(json);
			writer.flush();
			writer.close();
		} catch (Exception e) {
			System.out.println("Exception occurred: " + e);
		}
	}
}
