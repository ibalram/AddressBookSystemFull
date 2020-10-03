package com.cg.addressbook.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.cg.addressbook.Validator;
import com.cg.addressbook.exceptions.AddressValidationException;
import com.cg.addressbook.exceptions.EmailValidationException;
import com.cg.addressbook.exceptions.NameValidationException;
import com.cg.addressbook.exceptions.PhoneNumberValidationException;
import com.cg.addressbook.exceptions.ZipValidationException;

class ValidatorTest {

	@Test
	public void testValidateFirstName() {
		try {
			Validator obj = new Validator();
			String firstName = "Balram";
			assertEquals(true, obj.validateFirstName(firstName));
		} catch (NameValidationException e) {
			System.out.println(e.getMessage());
		}
	}

	@Test
	public void testValidateLastName() {
		try {
			Validator obj = new Validator();
			String lastName = "Singh";
			assertEquals(true, obj.validateLastName(lastName));
		} catch (NameValidationException e) {
			System.out.println(e.getMessage());
		}
	}
	
	@Test
	public void testValidateAddress() {
		try {
			Validator obj = new Validator();
			String address = "B-136, Jaipur";
			assertEquals(true, obj.validateAddress(address));
		} catch (AddressValidationException e) {
			System.out.println(e.getMessage());
		}
	}
	
	@Test
	public void testValidateCity() {
		try {
			Validator obj = new Validator();
			String city = "Jaipur";
			assertEquals(true, obj.validateCity(city));
		} catch (NameValidationException e) {
			System.out.println(e.getMessage());
		}
	}
	
	@Test
	public void testValidateState() {
		try {
			Validator obj = new Validator();
			String state = "Rajasthan";
			assertEquals(true, obj.validateState(state));
		} catch (NameValidationException e) {
			System.out.println(e.getMessage());
		}
	}
	
	@Test
	public void testValidateZip() {
		try {
			Validator obj = new Validator();
			String zip = "123456";
			assertEquals(true, obj.validateZip(zip));
		} catch (ZipValidationException e) {
			System.out.println(e.getMessage());
		}
	}

	@Test
	public void testValidateEmail() {
		try {
			Validator obj = new Validator();
			String email = "abc.xyz@cg.com";
			assertEquals(true, obj.validateEmail(email));
		} catch (EmailValidationException e) {
			System.out.println(e.getMessage());
		}
	}

	@Test
	public void testValidatePhoneNumber() {
		try {
			Validator obj = new Validator();
			String phoneNumber = "91 9469091234";
			assertEquals(true, obj.validatePhoneNumber(phoneNumber));
		} catch (PhoneNumberValidationException e) {
			System.out.println(e.getMessage());
		}
	}

}
