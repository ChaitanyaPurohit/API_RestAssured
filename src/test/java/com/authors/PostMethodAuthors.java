package com.authors;

import org.testng.annotations.Test;

import com.swaggerdata.TestData;
import com.swaggerdata.jsFileReader;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.nullValue;

public class PostMethodAuthors{
	
	jsFileReader jsReader=new jsFileReader();
	String validPayload = 	jsReader.readJson("/src/test/resources/requestPayloadForAuthors/CreateUserPayloads.json", "validPayload");
	@Test
	public void testCreateAuthorSuccess() {
		given().contentType(ContentType.JSON).body(validPayload).when().post(TestData.baseUrl + "/Authors").then()
				.statusCode(200).contentType(ContentType.JSON).body("firstName", equalTo("Chaitanya"))
				.body("lastName", equalTo("Purohit"));
	}

	@Test
	public void testCreateAuthorMissingLastName() {
		String payloadWithoutName = jsReader.readJson("/src/test/resources/requestPayloadForAuthors/CreateUserPayloads.json", "payloadWithoutName");

		given().contentType(ContentType.JSON).body(payloadWithoutName).when().post(TestData.baseUrl + "/Authors").then()
				.statusCode(200).body("lastName", nullValue());

	}

	@Test
	public void testCreateAuthor_DuplicateID() {
		given().contentType(ContentType.JSON).body(validPayload).when().post(TestData.baseUrl + "/Authors").then()
				.statusCode(200);

		given().contentType(ContentType.JSON).body(validPayload).when().post(TestData.baseUrl + "/Authors").then()
				.statusCode(200).body("id", equalTo(123));
	}

	@Test
	public void testCreateAuthor_EmptyPayload() {
		given().contentType(ContentType.JSON).body("{}").when().post(TestData.baseUrl + "/Authors").then()
				.statusCode(200).body("id", equalTo(0)).body("firstName", nullValue()).body("lastName", nullValue());
	}

	@Test
	public void testCreateAuthor_InvalidDataTypes() {
		String invalidPayloadNamesAsInt = jsReader.readJson("/src/test/resources/requestPayloadForAuthors/CreateUserPayloads.json", "invalidPayloadNamesAsInt");

		given().contentType(ContentType.JSON).body(invalidPayloadNamesAsInt).when().post(TestData.baseUrl + "/Authors").then()
				.statusCode(400).body("id", nullValue()).body("firstName", nullValue());
	}

	@Test
	public void testCreateAuthor_ExtraFields() {
		String payloadWithExtraFields = jsReader.readJson("/src/test/resources/requestPayloadForAuthors/CreateUserPayloads.json", "payloadWithExtraFields");
		given().contentType(ContentType.JSON).body(payloadWithExtraFields).when().post(TestData.baseUrl + "/Authors")
				.then().statusCode(200).body("extraField", nullValue());
	}

	@Test
	public void testCreateAuthor_NullFields() {
		String payloadWithNullFields = jsReader.readJson("/src/test/resources/requestPayloadForAuthors/CreateUserPayloads.json", "payloadWithNullFields");

		given().contentType(ContentType.JSON).body(payloadWithNullFields).when().post(TestData.baseUrl + "/Authors")
				.then().statusCode(400).body("id", nullValue()).body("firstName", nullValue());
	}

}