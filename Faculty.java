package application;
// Eduardo Cruz
// CS2012
// Description: The Faculty class contains constructors that creates objects representing a faculty member.

public class Faculty extends Employee {
	private String officeHours;
	private String rank;
	
	public Faculty() {
		this.officeHours = "N/A";
		this.rank = "N/A";
	}
	 
	public Faculty(String firstName, String lastName, String emailAddress, Address address, 
			String type, String number, String officeLocation, String salary, String officeHours, String rank) 
					throws PhoneNumberFormatException {
		super(firstName, lastName, emailAddress, address, type, number, officeLocation, salary);
		if(validateRank(rank)) {
			this.officeHours = officeHours;
		}
		else {
			System.exit(1);
		}
	}
    
	public Faculty(String firstName, String lastName, String emailAddress, Address address, 
			String officeLocation, String salary, String officeHours, String rank) 
					throws PhoneNumberFormatException {
		super(firstName, lastName, emailAddress, address, officeLocation, salary);
		if(validateRank(rank)) {
			this.officeHours = officeHours;
		}
		else {
			System.exit(1);
		}
	}
	
	public String getOfficeHours() {
		return this.officeHours;
	}

	public String getRank() {
		return this.rank;
	}

	public boolean validateRank(String RANK) {
		if(RANK.matches("full time") || RANK.matches("part time") ||
		   RANK.matches("Full Time") || RANK.matches("Part Time")) {
			this.rank = RANK;
			return true;
		}
		else {
			System.out.println("ERROR: Can only enter \"full time\" or \"part time\" for the rank.");
			return false;
		}
	}
	
	public boolean equals(Object obj) {
		if(!(obj instanceof Faculty)) {
			return false;
		}
		
		Faculty other = (Faculty)obj;
		
		boolean isOfficeHoursEqual = this.officeHours.equals(other.officeHours);
		boolean isRankEqual = this.rank.equals(other.rank);
		
		return super.equals(other) && isOfficeHoursEqual && isRankEqual;
	}
	
	public String toString() {
		String result = "";
		
		result += "Faculty," + super.getFirstName() + "," + super.getLastName() + "," + 
		          super.getEmailAddress() + "," + this.getOfficeLocation() + "," + this.getSalary() + "," +
				  this.officeHours + "," + this.rank + "\n" + super.getAddress() + super.getFormattedPhoneNumbers(); 
		
		return result;
	}
}