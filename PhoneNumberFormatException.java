package application;
// Eduardo Cruz
// CS2012
// Description: The PhoneNumberFormatException class uses a custom exception to check if a provided phone number entry is in the correct format.

public class PhoneNumberFormatException extends Exception {
	private static final long serialVersionUID = 1L;
	@SuppressWarnings("unused")
	private String phoneNumber;
	
	public PhoneNumberFormatException(String phoneNumber) {
		super("Incorrect phone number format for " +  phoneNumber + 
				"\n(format ex. 555-555-5555)");
		this.phoneNumber = phoneNumber;
	}
}