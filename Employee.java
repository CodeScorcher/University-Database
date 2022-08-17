package application;
// Eduardo Cruz
// CS2012
// Description: The Employee class contains constructors that creates objects representing an employee.
// This will serve as a superclass for the following classes: Faculty and Staff.

public class Employee extends Person {
	private String officeLocation;
	private String salary;
	
	public Employee() {
		this.officeLocation = "N/A";
		this.salary = "N/A";
	}
	 
	public Employee(String firstName, String lastName, String emailAddress, Address address, 
			String type, String number, String officeLocation, String salary) throws PhoneNumberFormatException{
		super(firstName, lastName, emailAddress, address, type, number);
		this.officeLocation = officeLocation;
		this.salary = salary;
	}
	
	public Employee(String firstName, String lastName, String emailAddress, Address address, 
			String officeLocation, String salary) throws PhoneNumberFormatException{
		super(firstName, lastName, emailAddress, address);
		this.officeLocation = officeLocation;
		this.salary = salary;
	}

	public String getOfficeLocation() {
		return this.officeLocation;
	}

	public String getSalary() {
		return this.salary;
	}

	public boolean equals(Object obj) {
		if(!(obj instanceof Employee)) {
			return false;
		}
		
		Employee other = (Employee)obj;
		
		boolean isOfficeLocationEqual = this.officeLocation.equals(other.officeLocation);
		boolean isSalaryEqual = this.salary == other.salary;
		
		return super.equals(other) && isOfficeLocationEqual && isSalaryEqual;
	}
	
	public String toString() {
		String result = "";
		
		result += "Employee," + super.getFirstName() + "," + super.getLastName() + "," + 
		          super.getEmailAddress() + ",$" + this.salary + "," + this.officeLocation + "\n" +
				  super.getAddress() + super.getFormattedPhoneNumbers();
		
		return result;
	}
}