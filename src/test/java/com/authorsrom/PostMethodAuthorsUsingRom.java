package com.authorsrom;

import org.testng.annotations.Test;

import com.authors.requestspecs.AuthorRequests;
import com.swaggerdata.jsFileReader;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.nullValue;

public class PostMethodAuthorsUsingRom {

	String validPayload = 	jsFileReader.readJson("/src/test/resources/requestPayloadForAuthors/CreateUserPayloads.json", "validPayload");

	@Test
	public void testCreateAuthorSuccess() {
		AuthorRequests.createAuthor(validPayload).then().statusCode(200).contentType("application/json")
				.body("firstName", equalTo("Chaitanya")).body("lastName", equalTo("Purohit"));
	}

	@Test
	public void testCreateAuthorMissingLastName() {
		String payloadWithoutName = jsFileReader.readJson("/src/test/resources/requestPayloadForAuthors/CreateUserPayloads.json", "payloadWithoutName");

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
		String invalidPayloadNamesAsInt = jsFileReader.readJson("/src/test/resources/requestPayloadForAuthors/CreateUserPayloads.json", "invalidPayloadNamesAsInt");

		AuthorRequests.createAuthor(invalidPayloadNamesAsInt).then().statusCode(400).body("id", nullValue())
				.body("firstName", nullValue());
	}

	@Test
	public void testCreateAuthor_ExtraFields() {
		String payloadWithExtraFields = jsFileReader.readJson("/src/test/resources/requestPayloadForAuthors/CreateUserPayloads.json", "payloadWithExtraFields");

		AuthorRequests.createAuthor(payloadWithExtraFields).then().statusCode(200).body("extraField", nullValue());
	}

	@Test
	public void testCreateAuthor_NullFields() {
		String payloadWithNullFields = jsFileReader.readJson("/src/test/resources/requestPayloadForAuthors/CreateUserPayloads.json", "payloadWithNullFields");

		AuthorRequests.createAuthor(payloadWithNullFields).then().statusCode(400).body("id", nullValue())
				.body("firstName", nullValue());
	}
}
