package com.ecommerce.support.pageobjects.dtos;

import java.util.Calendar;
import java.util.GregorianCalendar;

import com.github.javafaker.Faker;

public class CreateAccountInformationGenerator {
	
	public CreateAccountInformationDto getFakeAccountInformation() {
		Faker faker = new Faker();
		Calendar calendar = new GregorianCalendar();
		CreateAccountInformationDto dto = new CreateAccountInformationDto();
		
		dto.email = faker.name()
				.fullName()
				.toLowerCase()
				.replace(' ', '_') +
				calendar.get(Calendar.MONTH) +
				calendar.get(Calendar.DAY_OF_MONTH) + 
				calendar.get(Calendar.SECOND) +
				"@fake.com";

		dto.firstName = faker.name().firstName();
		dto.lastName = faker.name().lastName();
		dto.addressAlias = faker.funnyName().name();
		dto.addressFirstName = faker.name().firstName();
		dto.addressLastName = faker.name().lastName();
		dto.addressLine1 = faker.address().streetAddress();
		dto.addressLine2 = faker.address().secondaryAddress();
		dto.city = faker.address().city();
		dto.postalCode = faker.number().digits(5);
		dto.state = "Arizona";
		dto.country = "United States";
		dto.company = faker.company().name();
		dto.password = "1234Wow";
		dto.title = "Mr.";
		
		dto.dateOfBirth = String.format("%d-%d-%d", calendar.get(Calendar.YEAR)-37, 7, 7);
		dto.phoneHome  = faker.number().digits(9);
		dto.phoneMobile  = faker.number().digits(9);
		
		return dto;
	}

}
