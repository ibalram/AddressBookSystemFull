package com.cg.addressbook.service;

import java.sql.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.*;

import com.cg.addressbook.dto.Contact;

public class AddressBookDBService {
	private static AddressBookDBService addressBookDBService;
	private PreparedStatement addressBookStatement;

	private AddressBookDBService() {
	}

	public static AddressBookDBService getInstance() {
		if (addressBookDBService == null)
			addressBookDBService = new AddressBookDBService();
		return addressBookDBService;
	}

	private Connection getConnection() throws SQLException {
		String jdbcURL = "jdbc:mysql://localhost:3306/address_book_service?useSSL=false";
		String userName = "root";
		String password = "1234";
		Connection connection = null;
		System.out.println("Connecting to database:" + jdbcURL);
		connection = DriverManager.getConnection(jdbcURL, userName, password);
		System.out.println("Connection is successfull!!!" + connection);
		return connection;
	}

	public List<Contact> readData() {
		String sql = "select * from addressbook a inner join contact c on a.person_id=c.person_id;";
		return this.getContactUsingDB(sql);
	}

	private List<Contact> getContactUsingDB(String sql) {
		List<Contact> contactList = new ArrayList<>();
		try (Connection connection = this.getConnection()) {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			contactList = this.getContact(resultSet);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return contactList;
	}

	private List<Contact> getContact(ResultSet result) {
		List<Contact> contactList = new ArrayList<Contact>();
		try {
			while (result.next()) {
				String first_name = result.getString("first_name");
				String last_name = result.getString("last_name");
				String address = result.getString("address");
				String city = result.getString("city");
				String state = result.getString("state");
				String zip = result.getString("zip");
				String phone_number = result.getString("phone_number");
				String email = result.getString("email");
				contactList.add(new Contact(first_name, last_name, address, city, state, zip, phone_number, email));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return contactList;
	}

	public int updatePhoneNumber(String name, String phone_number) {
		String sql = String.format(
				"update contact set phone_number= '%s' where person_id in (select person_id from addressbook where first_name='%s');",
				phone_number, name);
		try (Connection connection = this.getConnection()) {
			Statement statement = connection.createStatement();
			return statement.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public List<Contact> getAddressBookData(String name) {
		List<Contact> contactList = null;
		if (this.addressBookStatement == null) {
			this.prepareStatementForContact();
		}
		try {
			addressBookStatement.setString(1, name);
			ResultSet resultSet = addressBookStatement.executeQuery();
			contactList = this.getContact(resultSet);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return contactList;
	}

	private void prepareStatementForContact() {
		try {
			Connection connection = this.getConnection();
			String sql = "select * from addressbook a inner join contact c on a.person_id=c.person_id where first_name=?;";
			addressBookStatement = connection.prepareStatement(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Contact> getContactsAddedInDateRange(LocalDate startDate, LocalDate endDate) {
		String sql = String.format(
				"select * from addressbook a inner join contact c on a.person_id=c.person_id where date_added between '%s' and '%s';",
				Date.valueOf(startDate), Date.valueOf(endDate));
		return this.getContactUsingDB(sql);
	}

	public List<Contact> getContactsByCityOrState(String city, String state) {
		String sql = String.format(
				"select * from addressbook a inner join contact c on a.person_id=c.person_id where city='%s' or state='%s';",
				city, state);
		return this.getContactUsingDB(sql);
	}

	public Contact addContactToAddressBookDB(String first_name, String last_name, String address, String city,
			String state, String zip, String phone_number, String email, LocalDate date_added) {
		int personId = -1;
		Contact contact = null;
		Connection connection = null;
		try {
			connection = this.getConnection();
			connection.setAutoCommit(false);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		try (Statement statement = connection.createStatement()) {
			String sql = String.format(
					"insert into addressbook(first_name, last_name, address, city, state, zip, date_added) "
							+ "values ('%s', '%s', '%s', '%s', '%s', '%s', '%s');",
					first_name, last_name, address, city, state, zip, Date.valueOf(date_added));
			int rowAffected = statement.executeUpdate(sql, statement.RETURN_GENERATED_KEYS);
			if (rowAffected == 1) {
				ResultSet resultSet = statement.getGeneratedKeys();
				if (resultSet.next())
					personId = resultSet.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				connection.rollback();
				return contact;
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		try (Statement statement = connection.createStatement()) {
			String sql = String.format("insert into contact (person_id, phone_number, email) values (%s, '%s', '%s');",
					personId, phone_number, email);
			int rowAffected = statement.executeUpdate(sql);
			if (rowAffected == 1) {
				contact = new Contact(first_name, last_name, address, city, state, zip, phone_number, email);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		System.out.println(contact);
		try {
			connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return contact;
	}

}
