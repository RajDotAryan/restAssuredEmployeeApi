package com.restAssuredEmployeeApi.testCases;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.AssertJUnit;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.restAssuredEmployeeApi.base.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;

public class GetEmployee extends TestBase {

	private static Logger logger = Logger.getLogger(GetEmployee.class.getName());

	boolean flag = false;


	@BeforeClass
	void getEmployeeDetails() throws InterruptedException{

		RestAssured.baseURI = url;
		httpRequest = RestAssured.given();
		response = httpRequest.request(Method.GET, "/employees/"+empIdValidation);

	}

	@Test
	void ValidateResponse() {
		logger.info("Started The Test Case "+GetEmployee.class.getSimpleName());
		int responseCode = response.getStatusCode();
		logger.info("Response Code is "+responseCode);
		if(responseCode==200) {
			String responseBody = response.getBody().asString();
			logger.info("Response Body is "+responseBody);

			JsonPath jsonPathEvaluator = response.jsonPath();

			String empId = jsonPathEvaluator.get("empId");

			logger.info("Response empId is " + empId);
			if(empIdValidation.equals(empId)) {
				logger.info("Successfully getting the single employee details");
				flag=true;
			}
		}else {
			logger.info("Response code is other than 200, expected is 200 and actual is " +responseCode);
		}

		Assert.assertTrue(flag, "Employee is not Deleted");
	}

}
