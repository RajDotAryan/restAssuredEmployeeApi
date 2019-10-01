package com.restassuredemployeeapi.testCases;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.restassuredemployeeapi.base.TestBase;
import com.restassuredemployeeapi.utilities.RestUtils;

import io.restassured.RestAssured;
import io.restassured.http.Method;

public class UpdateEmployee extends TestBase {

	private static Logger logger = Logger.getLogger(UpdateEmployee.class.getName());
	boolean flag = false;
	String empId = RestUtils.empId();
	String empName = RestUtils.empName();
	String empDepartment = RestUtils.empDepartment();

	@SuppressWarnings("unchecked")
	@BeforeMethod
	void updateEmployee() throws InterruptedException {
		RestAssured.baseURI = url;
		httpRequest = RestAssured.given();

		JSONObject requestParam = new JSONObject();
		requestParam.put("empId", empIdValidation);
		requestParam.put("empName", empName);
		requestParam.put("empDepartment", empDepartment);

		httpRequest.headers("Content-Type", "application/json");
		httpRequest.body(requestParam.toJSONString());
		response = httpRequest.request(Method.PUT, "employees/update/"+empIdValidation);
		Thread.sleep(2000);
	}

	@Test
	void validateResponseOfUpdateEmployee() {
		logger.info("Started The Test Case "+UpdateEmployee.class.getSimpleName());
		int responseCode = response.getStatusCode();
		logger.info("Response Code is "+responseCode);
		Assert.assertEquals(responseCode, 200, "Response status code is not validated");
			response = httpRequest.request(Method.GET, "/employees");
			String responseBody = response.getBody().asString();
			logger.info("Response Body is "+responseBody);
			
			softAssert.assertTrue(responseBody.contains(empName), "Emp Name is not present in the body");
			softAssert.assertTrue(responseBody.contains(empDepartment), "Emp Department is not present in the body");
			softAssert.assertAll();

	}
}
