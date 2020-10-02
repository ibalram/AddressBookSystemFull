package com.cg.addressbook;

import java.util.Scanner;

import com.cg.addressbook.dto.AddressBook;
import com.cg.addressbook.service.AddressBookService;

public class Executor {
	private static Scanner sc = new Scanner(System.in);
	static AddressBook addressBook;

	public static void main(String[] args) {
		System.out.println("Welcome to Address Book Program");
		System.out.println("--------------------------------");
		AddressBookService addressBookService = new AddressBookService(sc);
		addressBook = new AddressBook();
		addressBookService.addressBookOptions(addressBook);
	}
}
