package application;
//Eduardo Cruz
//CS2012-2
//Description: The PhoneNumber class uses constructors to create an object and contains methods that modify its data fields.

public class PhoneNumber {
	private String type;
	private String areaCode;
	private String prefix;
	private String suffix;
	
	public PhoneNumber() {
		this.type = "N/A";
		this.areaCode = "N/A";
		this.prefix = "N/A";
		this.suffix = "N/A";
	}
	
	public PhoneNumber(String type, String number) throws PhoneNumberFormatException {
		setPhoneNumber(number);
		this.type = type;
	}
	
	public void setPhoneNumber(String number) 
			throws PhoneNumberFormatException {
		if(number.matches("\\d{3}-\\d{3}-\\d{4}")) {
			String[] parts = number.split("-"); 
			this.areaCode = parts[0]; 
	    	this.prefix = parts[1]; 
			this.suffix = parts[2];
		}
		else {
			throw new PhoneNumberFormatException(number);
		}
	}
	
	public String getType() {
		return this.type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public String getAreaCode() {
		return this.areaCode;
	}
	
	public String getPrefix() {
		return this.prefix;
	}
	
	public String getSuffix() {
		return this.suffix;
	}
	
	public String getPhoneNumber() {
		String result = "";
		
		result += type + ": " + areaCode + "-" + prefix + "-" + suffix;
		
		return result;
	}
	
	@Override
	public boolean equals(Object o) {
		if(!(o instanceof PhoneNumber)) {
			return false;
		}		
		
		PhoneNumber other = (PhoneNumber)o;
		
		boolean isTypeEqual = this.type.equals(other.type);
		boolean isAreaCodeEqual = this.areaCode.equals(other.areaCode);
		boolean isPrefixEqual = this.prefix.equals(other.prefix);
		boolean isSuffixEqual = this.suffix.equals(other.suffix);
		
		return (isTypeEqual && isAreaCodeEqual 
				&& isPrefixEqual && isSuffixEqual);
	}
	
	public String toString() {
		String result = "";
		
		result += "Phone," + type + "," + areaCode + "-" + prefix + "-" + suffix;
		
		return result;
	}
}
