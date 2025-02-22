package com.Activities;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;

import org.testng.annotations.Test;

public class GetMethodActivities {
	
	@Test
	public void verifyGetAllActicitives() {
		given().when().get("https://fakerestapi.azurewebsites.net/api/v1/Activities").then().statusCode(200)
		.body("size()", greaterThan(0)).log().all();
	}
	
	@Test
	public void verifyGetContentTypeActivities() {
		given().when().get("https://fakerestapi.azurewebsites.net/api/v1/Activities").then()
				.contentType("application/json");
	}
	
	@Test
	public void verifyGetStatusCode() {
		given().when().get("https://fakerestapi.azurewebsites.net/api/v1/Activities").then()
		.log()
		.all()
		.assertThat()
		.statusCode(200);
		
	}
	
	@Test
	public void verifyGetActivitiesById() {
		given().when().get("https://fakerestapi.azurewebsites.net/api/v1/Activities/1").then()
		.statusCode(200).body("id",equalTo(1));
	}
	
	@Test
	public void VerifyGetNonExistingActivitiesById() {
		given().when().get("https://fakerestapi.azurewebsites.net/api/v1/Activities/123").then()
		.statusCode(404);
	}
	
	

	

}
