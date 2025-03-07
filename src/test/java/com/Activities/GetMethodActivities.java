package com.Activities;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import com.restAssured.base.BaseTest;

public class GetMethodActivities{
	
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
	
	@Test
	public void verifyGetActivitesWithValidData() {
		given().when().get("https://fakerestapi.azurewebsites.net/api/v1/Activities").then().log().all()
		.assertThat()
		.body("[0].id",Matchers.equalTo(1))
		.body("[0].title",Matchers.equalTo("Activity 1"))
		.body("[0].completed",Matchers.equalTo(false))
		.statusCode(200)
		.body("size()", greaterThan(0));
	}

	@Test
	public void VerifyGetActivitiesWithEmptyId() {
		given().when().get("https://fakerestapi.azurewebsites.net/api/v1/Activities/").then().log().all().assertThat()
		.statusCode(200);
	}
	
	@Test
	public void VerifyGetActivitiesWithInvalidEndpoint() {
		given().when().get("https://fakerestapi.azurewebsites.net/api/v1/Act/1").then().log().all()
		.assertThat().statusCode(404);
	}
	
	@Test
	public void VerifyGetActivitiesWithInvalidformatOfId() {
		given().when().get("https://fakerestapi.azurewebsites.net/api/v1/Activities/<123>").then().log().all()
		.assertThat().statusCode(400);
	}
	@Test
	public void VerifyGetActivitiesWithInvalidformatOfNegativeId() {
		given().when().get("https://fakerestapi.azurewebsites.net/api/v1/Activities/-123").then().log().all()
		.assertThat().statusCode(404);
	}
	
	@Test
	public void VerifyGetActivitiesInvalidprotocol() {
		given().when().post("https://fakerestapi.azurewebsites.net/api/v1/Activities/1").then().log().all()
		.assertThat().statusCode(405);
	}
	
	

}
