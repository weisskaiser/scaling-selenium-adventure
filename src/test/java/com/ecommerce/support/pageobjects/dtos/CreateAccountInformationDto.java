package com.ecommerce.support.pageobjects.dtos;

public class CreateAccountInformationDto {

	@Override
	public String toString() {
		return "CreateAccountInformationDto [" + (title != null ? "title=" + title + ", " : "")
				+ (firstName != null ? "firstName=" + firstName + ", " : "")
				+ (lastName != null ? "lastName=" + lastName + ", " : "")
				+ (password != null ? "password=" + password + ", " : "")
				+ (dateOfBirth != null ? "dateOfBirth=" + dateOfBirth + ", " : "")
				+ (addressFirstName != null ? "addressFirstName=" + addressFirstName + ", " : "")
				+ (addressLastName != null ? "addressLastName=" + addressLastName + ", " : "")
				+ (company != null ? "company=" + company + ", " : "")
				+ (addressLine1 != null ? "addressLine1=" + addressLine1 + ", " : "")
				+ (addressLine2 != null ? "addressLine2=" + addressLine2 + ", " : "")
				+ (addressAlias != null ? "addressAlias=" + addressAlias + ", " : "")
				+ (city != null ? "city=" + city + ", " : "") + (state != null ? "state=" + state + ", " : "")
				+ (postalCode != null ? "postalCode=" + postalCode + ", " : "")
				+ (country != null ? "country=" + country + ", " : "")
				+ (phoneHome != null ? "phoneHome=" + phoneHome + ", " : "")
				+ (phoneMobile != null ? "phoneMobile=" + phoneMobile + ", " : "")
				+ (email != null ? "email=" + email : "") + "]";
	}
	public String title;
	public String firstName;
	public String lastName;
	public String password;
	public String dateOfBirth;
	public String addressFirstName;
	public String addressLastName;
	public String company;
	public String addressLine1;
	public String addressLine2;
	public String addressAlias;
	public String city;
	public String state;
	public String postalCode;
	public String country;
	public String phoneHome;
	public String phoneMobile;
	public String email;

}
