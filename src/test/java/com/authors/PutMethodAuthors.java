package com.authors;

import org.testng.annotations.Test;

import com.swaggerdata.TestData;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.nullValue;
import static io.restassured.RestAssured.*;

public class PutMethodAuthors {
	String validPayload = "{ \"id\": 123, \"idBook\": 789, \"firstName\": \"Chaitanya\", \"lastName\": \"Purohit\"}";

	@Test
	public void testUpdateAuthorWithValidData() {
		given().header("Content-Type", "application/json").body(validPayload).when().put(TestData.baseUrl + "/123")
				.then().statusCode(200).body("id", equalTo(123)).body("idBook", equalTo(789))
				.body("firstName", equalTo("Chaitanya")).body("lastName", equalTo("Purohit"));
	}

	@Test
	public void testUpdateAuthorWithNonExistentID() {
		given().header("Content-Type", "application/json").body(validPayload).when().put(TestData.baseUrl + "/9999")
				.then().statusCode(200).body("error", nullValue());
	}
	@Test
	public void testUpdateAuthorWithInvalidIDFormat() {
		given().header("Content-Type", "application/json").body(validPayload).when().put(TestData.baseUrl + "/abc").then().statusCode(400)
				.body("error", nullValue());
	}

	@Test
	public void testUpdateAuthorWithEmptyPayload() {
		given().header("Content-Type", "application/json").body("{}").when().put(TestData.baseUrl + "/123").then().statusCode(200)
				.body("error", nullValue());
	}

	@Test
	public void testUpdateAuthorWithMissingFields() {
		String incompletePayload = """
				{
				  "id": 123,
				  "idBook": 789
				}
				""";

		given().header("Content-Type", "application/json").body(incompletePayload).when().put(TestData.baseUrl + "/123").then()
				.statusCode(200).body("error", nullValue());
	}

	@Test
	public void testUpdateAuthorWithUnexpectedFields() {
		String extraFieldsPayload = """
				{
				  "id": 123,
				  "idBook": 789,
				  "firstName": "Chaitanya",
				  "lastName": "Purohit",
				  "extraField": "Unexpected Value"
				}
				""";

		given().header("Content-Type", "application/json").body(extraFieldsPayload).when().put(TestData.baseUrl + "/123").then()
				.statusCode(200).body("error", nullValue());
	}

	@Test
	public void testUpdateAuthorWithNegativeID() {
		given().header("Content-Type", "application/json").body(validPayload).when().put(TestData.baseUrl + "/-123").then().statusCode(200)

				.body("error", nullValue());
	}

	@Test
	public void testUpdateAuthorWithSpecialCharacterID() {
		given().header("Content-Type", "application/json").body(validPayload).when().put(TestData.baseUrl + "/!@#").then().statusCode(400)

				.body("error", nullValue());
	}
}
