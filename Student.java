package application;
// Eduardo Cruz
// CS2012
// Description: The Student class contains constructors that creates objects representing a student.

public class Student extends Person {
	private String classStanding;
	private double gpa;
	
	public Student() {
		this.classStanding = "N/A";
		this.gpa = 0;
	}
	
	public Student(String firstName, String lastName, String emailAddress, Address address, 
			String type, String number, String classStanding, double gpa) throws PhoneNumberFormatException {
		super(firstName, lastName, emailAddress, address, type, number);
		this.classStanding = classStanding;
		this.gpa = gpa;
	}
	
	public Student(String firstName, String lastName, String emailAddress, Address address, 
			String classStanding, double gpa) throws PhoneNumberFormatException {
		super(firstName, lastName, emailAddress, address);
		this.classStanding = classStanding;
		this.gpa = gpa;
	}

	public String getClassStanding() {
		return this.classStanding;
	}

	public double getGpa() {
		return this.gpa;
	}

	public boolean equals(Object obj) {
		if(!(obj instanceof Student)) {
			return false;
		}
		
		Student other = (Student)obj;
		
		boolean isClassStandingEqual = this.classStanding.equals(other.classStanding);
		boolean isGpaEqual = this.gpa == other.gpa;
		
		return super.equals(other) && isClassStandingEqual && isGpaEqual;
	}
	
	public String toString() {
		String result = "";
		
		result += "Student," + super.getFirstName() + "," + super.getLastName() + "," + 
		          super.getEmailAddress() + "," + this.classStanding + "," + this.gpa + "\n" +
				  super.getAddress() + "\n" + super.getFormattedPhoneNumbers();
		
		return result;
	}
}