package com.restassuredemployeeapi.testCases;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.restassuredemployeeapi.base.TestBase;
import com.restassuredemployeeapi.utilities.RestUtils;

import io.restassured.RestAssured;
import io.restassured.http.Method;

public class AddEmployee extends TestBase {
	private static Logger logger = Logger.getLogger(AddEmployee.class.getName());
	boolean flag= false;
	String empId = RestUtils.empId();
	String empName = RestUtils.empName();
	String empDepartment = RestUtils.empDepartment();

	@SuppressWarnings("unchecked")
	@BeforeMethod
	void addEmployeeDetails() throws InterruptedException {
		//softAssert = new SoftAssert();
		RestAssured.baseURI = url;
		httpRequest = RestAssured.given();
		JSONObject requestParam = new JSONObject();
		requestParam.put("empId", empId);
		requestParam.put("empName", empName);
		requestParam.put("empDepartment", empDepartment);

		httpRequest.headers("Content-Type", "application/json");
		httpRequest.body(requestParam.toJSONString());
		response = httpRequest.request(Method.POST, "employees/create");
		Thread.sleep(3000);
	}

	@Test
	void validateResponseOfAddEmployee() {
		logger.info("Started The Test Case "+AddEmployee.class.getSimpleName());
		int responseCode = response.getStatusCode();
		logger.info("Response Code is "+responseCode);
		Assert.assertEquals(responseCode, 200, "Response status code is not validated");
		response = httpRequest.request(Method.GET, "/employees");
		String responseBody = response.getBody().asString();
		logger.info("Response Body is "+responseBody);
		softAssert.assertTrue(responseBody.contains(empId), "Emp Id is not present in the body");
		softAssert.assertTrue(responseBody.contains(empName), "Emp Name is not present in the body");
		softAssert.assertTrue(responseBody.contains(empDepartment), "Emp Department is not present in the body");
		softAssert.assertAll();
	}
}
