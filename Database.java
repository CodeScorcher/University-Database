package application;
// Eduardo Cruz
// CS2012-2
// Description: The Database class is a subclass of an ArrayList consisting of the Person class. It can have the superclass
// refer to subclasses through the ArrayList (Polymorphism). This database also contains methods for printing a specific group
// of people such as students, faculty, or staff.

import java.util.ArrayList;

@SuppressWarnings("serial")
public class Database extends ArrayList<Person> {

	// Default constructor
	public Database() {
		
	}
	
	// Method to print the data of all employees in database
	public void printEmployees() {
		for(int i = 0; i < size(); i++) {
			if(get(i) instanceof Employee) {
				System.out.println(get(i));
			}
		}
	}
	
	// Method to print the data of all students in database
	public void printStudents() {
		for(int i = 0; i < size(); i++) {
			if(get(i) instanceof Student) {
				System.out.println(get(i));
			}
		}
	}
	
	// Method to print the data of all faculty in database
	public void printFaculty() {
		for(int i = 0; i < size(); i++) {
			if(get(i) instanceof Faculty) {
				System.out.println(get(i));
			}
		}
	}
	
	// Method to print the data of all staff in database
	public void printStaff() {
		for(int i = 0; i < size(); i++) {
			if(get(i) instanceof Staff) {
				System.out.println(get(i));
			}
		}
	}
	
	// Getter method that returns the number of objects that are an instance of the Person class
	public int getNumberOfPeople() {
		int numberOfPeople = 0;
		
		for(int i = 0; i < size(); i++) {
			if(get(i) instanceof Person) {
				numberOfPeople++;
			}
		}
		
		return numberOfPeople;
	}
	
	// Getter method that returns the number of objects that are an instance of the Student class
	public int getNumberOfStudents() {
		int numberOfStudents = 0;
		
		for(int i = 0; i < size(); i++) {
			if(get(i) instanceof Student) {
				numberOfStudents++;
			}
		}
		
		return numberOfStudents;
	}
	
	// Another getter method that returns the number of objects that are an instance of the Employees class
	public int getNumberOfEmployees() {
		int numberOfEmployees = 0;
		
		for(int i = 0; i < size(); i++) {
			if(get(i) instanceof Employee) {
				numberOfEmployees++;
			}
		}
		
		return numberOfEmployees;
	}
	
	// And another getter method that returns the number of objects that are an instance of the Staff class
	public int getNumberOfStaff() {
		int numberOfStaff = 0;
		
		for(int i = 0; i < size(); i++) {
			if(get(i) instanceof Staff) {
				numberOfStaff++;
			}
		}
		
		return numberOfStaff;
	}
	
	// Getter method that returns the number of objects that are an instance of the Faculty class
	public int getNumberOfFaculty() {
		int numberOfFaculty = 0;
		
		for(int i = 0; i < size(); i++) {
			if(get(i) instanceof Faculty) {
				numberOfFaculty++;
			}
		}
		
		return numberOfFaculty;
	}
	
	// Unlike the previous getter methods, this getter returns the number of students whose class standing matches
	// with the parameter entered by the user
    public int getNumberOfStudentsByClassStanding(String classStanding) {
    	int numberOfStudents = 0;
		
		for(int i = 0; i < size(); i++) {
			if(get(i) instanceof Student) {
				Student other = (Student)get(i);
				if(other.getClassStanding() == classStanding) {
					numberOfStudents++;
				}
			}
		}
		
		return numberOfStudents;
    }
    
    // This method prints the data of all students who have a GPA that is greater than or equal to the parameter entered by the user
    public void printStudentsGreaterThanOrEqualToGPA(double gpa) {
    	for(int i = 0; i < size(); i++) {
    		if(get(i) instanceof Student) {
    			Student other = (Student)get(i);
    			
    			if(other.getGpa() >= gpa)
					System.out.println(get(i));
			}
        }
    }
    
    // This method prints the data of all students who have a GPA that is less than or equal to the parameter entered by the user
    public void printStudentsLessThanOrEqualToGPA(double gpa) {    	
    	for(int i = 0; i < size(); i++) {
    		if(get(i) instanceof Student) {
    			Student other = (Student)get(i);
    			if(other.getGpa() <= gpa) {
					System.out.println(get(i));
				}
			}
        }
    }
    
    public String toString() {
    	String result = "";
    	for(int i = 0; i < size(); i++)
    		result += get(i);
    	
    	return result;
    }
}