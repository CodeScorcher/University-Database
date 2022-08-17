package application;
// Eduardo Cruz
// CS2012
// Description: The Person class uses constructors to create an object that define a person.
// This will serve as the superclass for the following classes: Student, Employee, Faculty, and Staff.

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class Person {
	private static final AtomicInteger count = new AtomicInteger(0);
	private int id;
	private String firstName;
	private String lastName;
	private String emailAddress;
	private Address address;
	private ArrayList<PhoneNumber> phoneNumbers;
	
	public Person() {
		this.id = count.incrementAndGet();
		this.phoneNumbers = new ArrayList<PhoneNumber>();
		this.firstName = "N/A";
		this.lastName = "N/A";
		this.emailAddress = "N/A";
	}
	
	public Person(String firstName, String lastName, String emailAddress,
			Address address, String type, String number) throws PhoneNumberFormatException {
		this.id = count.incrementAndGet();
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailAddress = emailAddress;
		this.address = address;
		this.phoneNumbers = new ArrayList<PhoneNumber>();
		phoneNumbers.add(new PhoneNumber(type, number));
	}
	
	public Person(String firstName, String lastName, String emailAddress, Address address) throws PhoneNumberFormatException {
		this.id = count.incrementAndGet();
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailAddress = emailAddress;
		this.address = address;
		this.phoneNumbers = new ArrayList<PhoneNumber>();
	}
	
	public int getId() {
		return this.id;
	}
	
	public String getFirstName() {
		return this.firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return this.lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getEmailAddress() {
		return this.emailAddress;
	}
	
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	
	public Address getAddress() {
		return this.address;
	}
	
	public void setAddress(Address address) {
		this.address = address;
	}
	
	public ArrayList<PhoneNumber> getPhoneNumbers() {
		return this.phoneNumbers;
	}
	
	public String getFormattedPhoneNumbers() {
		String formattedPhoneNumbers = "";
		
		for(int i = 0; i < phoneNumbers.size(); i++) {
			if(i == 0) 
				formattedPhoneNumbers += phoneNumbers.get(i).getPhoneNumber() + "\n";
		    else if(i < phoneNumbers.size()) 
				formattedPhoneNumbers += phoneNumbers.get(i).getPhoneNumber() + "\n";
		    else
		    	formattedPhoneNumbers += phoneNumbers.get(i).getPhoneNumber();
		}	
		return formattedPhoneNumbers;
	}
	
	public void addPhoneNumber(String type, String number) 
			throws PhoneNumberFormatException {
		phoneNumbers.add(new PhoneNumber(type, number));
	}
	
	public boolean equals(Object obj) {
		if(!(obj instanceof Person)) {
			return false;
		}
		
		Person other = (Person) obj;
		
		boolean isFirstNameEqual = this.firstName.equals(other.firstName);
		boolean isLastNameEqual = this.lastName.equals(other.lastName);
		boolean isEmailAddressEqual = this.emailAddress.equals(other.emailAddress);
		boolean isAddressEqual = this.address.equals(other.address);
		boolean isPhoneNumbersEqual = this.phoneNumbers.equals(other.phoneNumbers);
		
		return isFirstNameEqual && isLastNameEqual && 
			   isEmailAddressEqual && isAddressEqual && isPhoneNumbersEqual;
	}
	
	public String toString() {
		String result = ""; 
		
		result +=  "Person," + this.firstName + "," + this.lastName + "," + 
					this.emailAddress + "\n" + this.address + "\n";
		
		for(int i = 0; i < phoneNumbers.size(); i++) {
			if(i == 0) 
				result += phoneNumbers.get(i) + "\n";
		    else 
				result += phoneNumbers.get(i) + "\n";
		}
		
		if(phoneNumbers.size() == 0)
			result += "\n";
		
		return result;
	}
}