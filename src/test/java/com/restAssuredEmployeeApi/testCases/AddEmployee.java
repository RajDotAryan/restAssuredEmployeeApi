package com.restAssuredEmployeeApi.testCases;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.AssertJUnit;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.restAssuredEmployeeApi.base.TestBase;
import com.restAssuredEmployeeApi.utilities.RestUtils;

import io.restassured.RestAssured;
import io.restassured.http.Method;

public class AddEmployee extends TestBase {

	private static Logger logger = Logger.getLogger(AddEmployee.class.getName());

	boolean flag= false;

	String empId = RestUtils.empId();
	String empName = RestUtils.empName();
	String empDepartment = RestUtils.empDepartment();

	@SuppressWarnings("unchecked")
	@BeforeClass
	void addEmployeeDetails() throws InterruptedException {

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
	void ValidateResponse() {
		int responseCode = response.getStatusCode();
		logger.info("Response Code is "+responseCode);
		if(responseCode==200) {
			response = httpRequest.request(Method.GET, "/employees");
			String responseBody = response.getBody().asString();
			logger.info("Response Body is "+responseBody);

			if(responseBody.contains(empId) && responseBody.contains(empName) && responseBody.contains(empDepartment)) {
				logger.info("Employee is Added Successfully");
				flag = true;
			}
		}else {
			logger.info("Response code is other than 200, expected is 200 and actual is " +responseCode);
		}

		Assert.assertTrue(flag, "Employee is not Added");

	}

}
