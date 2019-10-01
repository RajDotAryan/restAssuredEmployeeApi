package com.restassuredemployeeapi.base;

import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.BeforeTest;
import org.testng.asserts.SoftAssert;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TestBase {

	public static RequestSpecification httpRequest;
	public static Response response;
	public static String url = "http://localhost:9090/company";
	public String empIdValidation = "649";
	
	public static SoftAssert softAssert = new SoftAssert();

	@BeforeTest
	public void setUp() {
		PropertyConfigurator.configure("log4j.properties");
	}
}


