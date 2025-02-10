package com.authors;

import org.testng.annotations.Test;

import com.swaggerdata.TestData;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.equalTo;

public class GetMethodAuthors {
	@Test
	public void testGetAllAuthors() {
		given().when().get("https://fakerestapi.azurewebsites.net/api/v1/Authors").then().statusCode(200)
				.body("size()", greaterThan(0)).log().all();
	}

	@Test
	public void testGetAuthorsByBookId() {
		given().when().get(TestData.baseUrl + "/authors/books/1").then()
				.statusCode(200).body("size()", greaterThan(0));
	}

	@Test
	public void testGetAuthorById() {
		given().when().get(TestData.baseUrl +"/1").then().statusCode(200).body("id",
				equalTo(1));
	}

	@Test
	public void testGetNonExistingAuthorById() {
		given().when().get(TestData.baseUrl + "/12345").then().statusCode(404);
	}

	@Test
	public void testGetAuthorsWithInvaliIdFormat() {
		given().when().get(TestData.baseUrl + "/abc").then().statusCode(400);
	}
}
