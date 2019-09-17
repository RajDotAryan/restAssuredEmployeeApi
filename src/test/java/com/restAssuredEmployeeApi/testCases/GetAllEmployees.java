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

public class GetAllEmployees extends TestBase {

	private static Logger logger = Logger.getLogger(GetAllEmployees.class.getName());

	boolean flag = false;

	@BeforeClass
	void getAllEmployeesDetails() throws InterruptedException{

		RestAssured.baseURI = url;
		httpRequest = RestAssured.given();
		response = httpRequest.request(Method.GET, "/employees");

	}

	@Test
	void ValidateResponse() {
		int responseCode = response.getStatusCode();
		logger.info("Response Code is "+responseCode);
		if(responseCode==200) {
			String responseBody = response.getBody().asString();
			logger.info("Response Body is "+responseBody);
			JsonPath jsonPathEvaluator = response.jsonPath();
			String empId = jsonPathEvaluator.get("[0].empId");
			logger.info("Response empId is " + empId);
			if(empId!=null) {
				logger.info("Successfully getting the All employees details");
				flag=true;
			}
		}else {
			logger.info("Response code is other than 200, expected is 200 and actual is " +responseCode);
		}
		
		Assert.assertTrue(flag, "Getting All employees details failed");

	}

}
