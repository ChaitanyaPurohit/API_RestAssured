package com.authorsrom;

import org.testng.annotations.Test;

import com.authors.requestspecs.AuthorRequests;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.nullValue;

public class PostMethodAuthorsUsingRom {

	String validPayload = "{ \"id\": 123, \"idBook\": 789, \"firstName\": \"Chaitanya\", \"lastName\": \"Purohit\"}";

	@Test
	public void testCreateAuthorSuccess() {
		AuthorRequests.createAuthor(validPayload).then().statusCode(200).contentType("application/json")
				.body("firstName", equalTo("Chaitanya")).body("lastName", equalTo("Purohit"));
	}

	@Test
	public void testCreateAuthorMissingLastName() {
		String payloadWithoutName = "{ \"id\": 123, \"idBook\": 789, \"firstName\": \"chaitanya\" }";

		AuthorRequests.createAuthor(payloadWithoutName).then().statusCode(200).body("lastName", nullValue());
	}

	@Test
	public void testCreateAuthor_DuplicateID() {
		AuthorRequests.createAuthor(validPayload).then().statusCode(200);

		AuthorRequests.createAuthor(validPayload).then().statusCode(200).body("id", equalTo(123));
	}

	@Test
	public void testCreateAuthor_EmptyPayload() {
		AuthorRequests.createAuthor("{}").then().statusCode(200).body("id", equalTo(0))
				.body("firstName", nullValue()).body("lastName", nullValue());
	}

	@Test
	public void testCreateAuthor_InvalidDataTypes() {
		String invalidPayload = "{ \"id\": \"invalid\", \"idBook\": \"invalid\", \"firstName\": 123, \"lastName\": 456 }";

		AuthorRequests.createAuthor(invalidPayload).then().statusCode(400).body("id", nullValue())
				.body("firstName", nullValue());
	}

	@Test
	public void testCreateAuthor_ExtraFields() {
		String payloadWithExtraFields = "{ \"id\": 123, \"idBook\": 789, \"firstName\": \"Chaitanya\", \"lastName\": \"Purohit\", \"extraField\": \"extraValue\" }";

		AuthorRequests.createAuthor(payloadWithExtraFields).then().statusCode(200).body("extraField", nullValue());
	}

	@Test
	public void testCreateAuthor_NullFields() {
		String payloadWithNullFields = "{ \"id\": null, \"idBook\": null, \"firstName\": null, \"lastName\": null }";

		AuthorRequests.createAuthor(payloadWithNullFields).then().statusCode(400).body("id", nullValue())
				.body("firstName", nullValue());
	}
}
