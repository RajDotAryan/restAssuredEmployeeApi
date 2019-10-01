package com.restassuredemployeeapi.testCases;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.restassuredemployeeapi.base.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;

public class GetEmployee extends TestBase {

	private static Logger logger = Logger.getLogger(GetEmployee.class.getName());
	boolean flag = false;

	@BeforeMethod
	void getEmployeeDetails() throws InterruptedException{
		RestAssured.baseURI = url;
		httpRequest = RestAssured.given();
		response = httpRequest.request(Method.GET, "/employees/"+empIdValidation);
	}

	@Test
	void validateResponseOfGetEmployee() throws Exception {
		logger.info("Started The Test Case "+GetEmployee.class.getSimpleName());
		int responseCode = response.getStatusCode();
		logger.info("Response Code is "+responseCode);
		Assert.assertEquals(responseCode, 200, "Response status code is not validated");
		String responseBody = response.getBody().asString();
		logger.info("Response Body is "+responseBody); 
		JsonPath jsonPathEvaluator = response.jsonPath(); 
		String empId = jsonPathEvaluator.get("empId");
		logger.info("Response empId is " + empId);
		  
		softAssert.assertTrue(empIdValidation.equals(empId),"Emp Id is not present in the body"); 
		softAssert.assertAll();
		 
			
	}
}
