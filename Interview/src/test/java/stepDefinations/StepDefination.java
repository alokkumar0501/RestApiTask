package stepDefinations;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import resource.EmployeeResource;
import utility.Utility;
import static io.restassured.RestAssured.*;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import java.io.IOException;

public class StepDefination extends Utility {
	RequestSpecification req;
	Response res;
	JsonPath js;
	int count;
	
	@Given("Header of get product")
	public void header_of_get_product() throws IOException {
	        req=given().spec(requestSpecification());
	}
	
	@When("User call {string} with {string} https request")
	public void user_call_with_https_request(String resource, String httpMethod) {
		EmployeeResource resourceApi = EmployeeResource.valueOf(resource);
		System.out.println(resourceApi.getResource());
		if (httpMethod.equalsIgnoreCase("GET")) {
			res = req.when().get(resourceApi.getResource());
		} else if (httpMethod.equalsIgnoreCase("POST")) {
			res = req.when().post(resourceApi.getResource());
		} else if (httpMethod.equalsIgnoreCase("DELETE")) {
			res = req.when().delete(resourceApi.getResource());
		}
	}
	
	@Then("The Api call got success with Status code {string}")
	public void the_Api_call_got_success_with_Status_code(String statusCode) {
		assertEquals(res.getStatusCode(), Integer.parseInt(statusCode));
		System.out.println(res.asString());
	}
	
	@Then("User Will get the response as JSON")
	public void user_Will_get_the_response_as_JSON() {
		String contentType = res.getContentType();
	   assertTrue(contentType.contains("application/json"));
	}
	
	@Then("user will get {string},{string},{string},{string},{string}")
	public void user_will_get(String expStatus, String expAge, String expRole, String expDob, String expMessage) {
		String actualStatus = getJsonPath(res,"status");
		assertEquals(actualStatus, expStatus);
		js = getJsonPath(res);
		count = js.getInt("employeeData.size");
		for(int i=0;i<count;i++)
		{
			 int actualAge=js.get("employeeData["+i+"].age");
			 assertEquals(String.valueOf(actualAge), expAge);
			 String actualRole=js.get("employeeData["+i+"].role");
			 assertEquals(actualRole, expRole);
			 String actualDob=js.get("employeeData["+i+"].dob");
			 assertEquals(actualDob, expDob);
		}
		String actualMessage=getJsonPath(res,"message");
		assertEquals(actualMessage, expMessage);	
	}
	
	@Then("user will get Company name as {string}")
	public void user_will_get_Company_name_as(String expCompany) {
		for(int i=0;i<count;i++)
		{
			 String actualCompany=js.get("employeeData["+i+"].company");
			 assertEquals(String.valueOf(actualCompany), expCompany);
		}
	}

}
