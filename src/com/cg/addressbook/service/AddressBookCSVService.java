package com.cg.addressbook.service;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.cg.addressbook.dto.Contact;
import com.opencsv.*;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

public class AddressBookCSVService {
	private String CONTACT_FILE_NAME = "./contactsCSV.csv";

	public List<Contact> readData() {
		List<Contact> contactList = null;
		try {
			Reader reader = Files.newBufferedReader(Paths.get(CONTACT_FILE_NAME));
			CsvToBean<Contact> csvUser = new CsvToBeanBuilder<Contact>(reader).withType(Contact.class)
					.withIgnoreLeadingWhiteSpace(true).build();
			contactList = csvUser.parse();
		} catch (Exception e) {
			System.out.println("Exception occured: " + e);
		}
		return contactList;
	}

	public void writeData(List<Contact> contactList) {
		try {
			Writer writer = Files.newBufferedWriter(Paths.get(CONTACT_FILE_NAME));
			StatefulBeanToCsv<Contact> beanToCsv = new StatefulBeanToCsvBuilder<Contact>(writer)
					.withQuotechar(CSVWriter.NO_QUOTE_CHARACTER).build();
			beanToCsv.write(contactList);
			writer.flush();
		} catch (IOException | CsvDataTypeMismatchException | CsvRequiredFieldEmptyException e) {
			System.out.println("Exception occurred: " + e);
		}
	}
}
