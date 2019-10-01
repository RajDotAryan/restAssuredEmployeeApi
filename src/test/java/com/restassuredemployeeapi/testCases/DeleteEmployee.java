package com.restassuredemployeeapi.testCases;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.restassuredemployeeapi.base.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;

public class DeleteEmployee extends TestBase {
	private static Logger logger = Logger.getLogger(DeleteEmployee.class.getName());
	boolean flag = false;
	String empId = null;

	@BeforeMethod
	void deleteEmployee() throws InterruptedException {
		RestAssured.baseURI = url;
		httpRequest = RestAssured.given();
		response = httpRequest.request(Method.GET, "/employees");
		JsonPath jsonPathEvaluator = response.jsonPath();
		empId = jsonPathEvaluator.get("[0].empId");
		logger.info("Response empId is " + empId);
		response = httpRequest.request(Method.DELETE, "/employees/"+empId);
		Thread.sleep(2000);
	}

	@Test
	void validateResponseOfDeleteEmployee() {
		logger.info("Started The Test Case "+DeleteEmployee.class.getSimpleName());
		int responseCode = response.getStatusCode();
		logger.info("Response Code is "+responseCode);
		Assert.assertEquals(responseCode, 200, "Response status code is not validated");
		response = httpRequest.request(Method.GET, "/employees");
		String responseBody = response.getBody().asString();
		logger.info("Response Body is "+responseBody);
		
		softAssert.assertNotEquals(responseBody.contains(empId), "Emp Id is not present in the body");
		softAssert.assertAll();
	}
}
