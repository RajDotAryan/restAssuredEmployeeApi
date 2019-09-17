package com.restAssuredEmployeeApi.base;

import org.testng.annotations.BeforeMethod;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.BeforeClass;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TestBase {

	public static RequestSpecification httpRequest;
	public static Response response;
	public static String url = "http://localhost:9090/company";
	
	public String empIdValidation = "000";
	
	@BeforeMethod
	@BeforeClass
	public void setUp() {
		
		PropertyConfigurator.configure("log4j.properties");

	}
	
}


