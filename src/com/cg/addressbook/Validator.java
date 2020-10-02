package com.cg.addressbook;

import java.util.regex.Pattern;
import com.cg.addressbook.exceptions.*;

public class Validator {
	private String namePattern = "^[A-Z]{1}[A-Za-z]{2,}";
	private String addressPattern = "^[a-zA-Z0-9-, ]+";
	private String zipPattern = "^[0-9]{2,}";
	private String emailPattern = "^[a-zA-Z0-9+_-]+([.][a-zA-Z0-9]+)*@([a-zA-Z0-9]+)([.][a-z]+)?[.][a-z]{2,}$";
	private String phoneNumberPattern = "^[0-9]{1,}[ ][1-9][0-9]{9}$";
	
	public boolean validateFirstName(String firstName) throws NameValidationException {
		if (Pattern.matches(namePattern, firstName))
			return true;
		else
			throw new NameValidationException("Invalid First Name, Please enter valid first name");
	}

	public boolean validateLastName(String lastName) throws NameValidationException {
		if (Pattern.matches(namePattern, lastName))
			return true;
		else
			throw new NameValidationException("Invalid Last Name, Please enter valid last name");
	}
	
	public boolean validateAddress(String address) throws AddressValidationException {
		if (Pattern.matches(addressPattern, address))
			return true;
		else
			throw new AddressValidationException("Invalid Address, Please enter valid address");
	}
	
	public boolean validateCity(String city) throws NameValidationException {
		if (Pattern.matches(namePattern, city))
			return true;
		else
			throw new NameValidationException("Invalid City, Please enter valid city");
	}
	
	public boolean validateState(String state) throws NameValidationException {
		if (Pattern.matches(namePattern, state))
			return true;
		else
			throw new NameValidationException("Invalid State, Please enter valid state");
	}
	
	public boolean validateZip(String zip) throws ZipValidationException {
		if (Pattern.matches(zipPattern, zip))
			return true;
		else
			throw new ZipValidationException("Invalid Zip, Please enter valid zip");
	}
	
	public boolean validateEmail(String email) throws EmailValidationException {
		if (Pattern.matches(emailPattern, email))
			return true;
		else
			throw new EmailValidationException("Invalid Email, Please enter valid email");
	}

	public boolean validatePhoneNumber(String phoneNumber) throws PhoneNumberValidationException {
		if (Pattern.matches(phoneNumberPattern, phoneNumber))
			return true;
		else
			throw new PhoneNumberValidationException("Invalid Phone Number, Please enter valid phone number");
	}
}
