package com.restassuredemployeeapi.utilities;

import org.apache.commons.lang3.RandomStringUtils;

public class RestUtils {
	
	public static String empId() {
		String generatedString =  RandomStringUtils.randomNumeric(3);
		return generatedString;
	}
	
	public static String empName() {
		String generatedString =  RandomStringUtils.randomAlphabetic(2);
		return ("Raj "+generatedString);
	}
	
	public static String empDepartment() {
		String generatedString =  RandomStringUtils.randomAlphabetic(5);
		return ("Testing "+generatedString);
	}
		
}
