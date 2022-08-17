package application;
//Eduardo Cruz
//CS2012
//Description: The Address class contains constructors to create objects representing a home address. 

public class Address {
	private String streetNumber;
	private String apartmentNumber;
	private String streetName;
	private String city;
	private String state;
	private String zipCode;
	
	public Address() {
		this.streetNumber = "N/A";
		this.apartmentNumber = "N/A";
		this.streetName = "N/A";
		this.city = "N/A";
		this.state = "N/A";
		this.zipCode = "N/A";
	}
	
	public Address(String streetNumber, String streetName, 
		       String city, String state, String zipCode) {
		this.streetNumber = streetNumber;
		this.apartmentNumber = "N/A";
		this.streetName = streetName;
		this.city = city;
		this.state = state;
		this.zipCode = zipCode;
	}
	
	public Address(String streetNumber, String apartmentNumber, String streetName, 
			       String city, String state, String zipCode) {
		this.streetNumber = streetNumber;
		this.apartmentNumber = apartmentNumber;
		this.streetName = streetName;
		this.city = city;
		this.state = state;
		this.zipCode = zipCode;
	}
	
	public String getStreetNumber() {
		return this.streetNumber;
	}
	public void setStreetNumber(String streetNumber) {
		this.streetNumber = streetNumber;
	}
	
	public String getApartmentNumber() {
		return this.apartmentNumber;
	}
	public void setApartmentNumber(String apartmentNumber) {
		this.apartmentNumber = apartmentNumber;
	}
	
	public String getStreetName() {
		return this.streetName;
	}
	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}
	
	public String getCity() {
		return this.city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
	public String getState() {
		return this.state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	
	@Override
	public boolean equals(Object o) {
		if(!(o instanceof Address)) {
			return false;
		}		
		
		Address other = (Address) o;
		
		boolean isStreetNumberEqual = this.streetNumber.equals(other.streetNumber);
		boolean isApartmentNumberEqual = this.apartmentNumber.equals(other.apartmentNumber);
		boolean isStreetNameEqual = this.streetName.equals(other.streetName);
		boolean isCityEqual = this.city.equals(other.city);
		boolean isStateEqual = this.state.equals(other.state);
		boolean isZipCodeEqual = this.zipCode.equals(other.zipCode);
		
		return (isStreetNumberEqual && isApartmentNumberEqual 
				&& isStreetNameEqual && isCityEqual
				&& isStateEqual && isZipCodeEqual);
	}
	
	public String toString() {
		String result = "";
		
		if(apartmentNumber.equals("N/A") || apartmentNumber.equals("")) {
			result += "Address," + this.streetNumber + "," + this.streetName +
					  "," + this.city + "," + this.state + "," + this.zipCode;
		}
		else {
			result += "Address," + this.streetNumber + "," + "Apt. " + 
					this.apartmentNumber + "," + this.streetName + "," + 
					this.city + "," + this.state + "," + this.zipCode + "\n";
		}
                      		
		return result;
	}
}
