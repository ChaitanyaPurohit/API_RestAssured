package com.authors;

import org.testng.annotations.Test;

import com.authorsdata.TestData;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.nullValue;

public class PostMethodauthors {
	String validPayload = "{ \"id\": 123, \"idBook\": 789, \"firstName\": \"Chaitanya\", \"lastName\": \"Purohit\"}";

	@Test
	public void testCreateAuthorSuccess() {
		given().contentType(ContentType.JSON).body(validPayload).when().post(TestData.baseUrl).then().statusCode(200)
				.contentType(ContentType.JSON).body("firstName", equalTo("Chaitanya"))
				.body("lastName", equalTo("Purohit"));
	}

	@Test
	public void testCreateAuthorMissingLastName() {
		String payloadWithoutName = "{\"id\": 123, \"idBook\": 789, \"firstName\": \"chaitanya\"}";

		given().contentType(ContentType.JSON).body(payloadWithoutName).when().post(TestData.baseUrl).then()
				.statusCode(200).body("lastName", nullValue());

	}

	@Test
	public void testCreateAuthor_DuplicateID() {
		given().contentType(ContentType.JSON).body(validPayload).when().post(TestData.baseUrl).then().statusCode(200);

		given().contentType(ContentType.JSON).body(validPayload).when().post(TestData.baseUrl).then().statusCode(200)
				.body("id", equalTo(123));
	}

	@Test
	public void testCreateAuthor_EmptyPayload() {
		given().contentType(ContentType.JSON).body("{}").when().post(TestData.baseUrl).then().statusCode(200)
				.body("id", equalTo(0)).body("firstName", nullValue()).body("lastName", nullValue());
	}

	@Test
	public void testCreateAuthor_InvalidDataTypes() {
		String invalidPayload = "{ \"id\": \"invalid\", \"idBook\": \"invalid\", \"firstName\": 123, \"lastName\": 456 }";

		given().contentType(ContentType.JSON).body(invalidPayload).when().post(TestData.baseUrl).then().statusCode(400)
				.body("id", nullValue()).body("firstName", nullValue());
	}

	@Test
	public void testCreateAuthor_ExtraFields() {
		String payloadWithExtraFields = "{ \"id\": 123, \"idBook\": 789, \"firstName\": \"Chaitanya\", \"lastName\": \"Purohit\", \"extraField\": \"extraValue\" }";

		given().contentType(ContentType.JSON).body(payloadWithExtraFields).when().post(TestData.baseUrl).then()
				.statusCode(200).body("extraField", nullValue());
	}

	@Test
	public void testCreateAuthor_NullFields() {
		String payloadWithNullFields = "{ \"id\": null, \"idBook\": null, \"firstName\": null, \"lastName\": null }";

		given().contentType(ContentType.JSON).body(payloadWithNullFields).when().post(TestData.baseUrl).then()
				.statusCode(400)
				.body("id", nullValue())
				.body("firstName", nullValue());
	}

}
