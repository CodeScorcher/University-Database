package application;
// Eduardo Cruz
// CS2012
// Description: The Staff class creates objects representing a staff member.

public class Staff extends Employee {
	private String jobTitle;
	
	public Staff() {
		this.jobTitle = "N/A";
	}
	
	public Staff(String firstName, String lastName, String emailAddress, Address address, String type, 
				 String number, String officeLocation, String salary, String jobTitle) throws PhoneNumberFormatException {
		super(firstName, lastName, emailAddress, address, type, number, officeLocation, salary);
		this.jobTitle = jobTitle;
	}
	
	public Staff(String firstName, String lastName, String emailAddress, Address address,
				 String officeLocation, String salary, String jobTitle) throws PhoneNumberFormatException {
		super(firstName, lastName, emailAddress, address, officeLocation, salary);
		this.jobTitle = jobTitle;
	}

	public String getJobTitle() {
		return jobTitle;
	}
	
	public boolean equals(Object obj) {
		if(!(obj instanceof Staff))
			return false;
		
		Staff other = (Staff)obj;
		boolean isJobTitleEqual = this.jobTitle.equals(other.jobTitle);
		
		return  super.equals(other) && isJobTitleEqual;
	}
	
	public String toString() {
		String result = "";
		
		result += "Staff," + super.getFirstName() + "," + super.getLastName() + "," + 
		          super.getEmailAddress() + "," + super.getOfficeLocation() + "," +
				  super.getSalary() + "," + this.jobTitle + "\n" + super.getAddress() + 
				  super.getFormattedPhoneNumbers();
		
		return result;
	}
}