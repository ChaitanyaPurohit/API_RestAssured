package com.Activities;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.nullValue;

import org.testng.annotations.Test;

import com.restAssured.base.BaseTest;
import com.swaggerdata.TestData;

public class PutMethodActivities{

	String validPayload = "{ \"id\": 23, \"title\":\"New1\",\"completed\":true}";

	@Test
	public void testUpdateActivitiesWithValidPayload() {
		given().header("Content-Type", "application/json").body(validPayload).when()
				.put(TestData.baseUrlActivity + "/23").then().statusCode(200).body("id", equalTo(23))
				.body("title", equalTo("New1")).body("completed", equalTo(true));
	}

	@Test
	public void testUpdateActivitesWithDiffValidData() {
		String validPayload = "{ \"id\": 26, \"title\":\"suju activity\",\"completed\":false}";

		given().header("Content-Type", "application/json").body(validPayload).when()
				.put(TestData.baseUrlActivity + "/26").then().statusCode(200).body("title", equalTo("suju activity"))
				.body("completed", equalTo(false));
	}

	@Test
	public void testUpdatingActivityWithSameData() {
		String validPayload = "{ \"id\": 3, \"title\":\"suju activity\",\"completed\":true}";

		given().header("Content-Type", "application/json").body(validPayload).when()
				.put(TestData.baseUrlActivity + "/3").then().statusCode(200).body("title", equalTo("suju activity"));
	}

	@Test
	public void testUpdatingActivityWithcompleted() {
		String validPayload = "{ \"id\": 4, \"title\":\"suju activity\",\"completed\":true}";

		given().header("Content-Type", "application/json").body(validPayload).when()
				.put(TestData.baseUrlActivity + "/4").then().statusCode(200).body("completed", equalTo(true));
	}

	@Test
	public void testUpdateActivitiesWithMissingFields() {
		String incompletePayload = """
				{
				  "id": 123
				}
				""";

		given().header("Content-Type", "application/json").body(incompletePayload).when()
				.put(TestData.baseUrlActivity + "/123").then().statusCode(200).body("id", equalTo(123))
				.body("title", equalTo(null)).body("completed", equalTo(false));
	}

	@Test
	public void testUpdateActivitiesWithInvalidIdFormat() {
		given().header("Content-Type", "application/json").body(validPayload).when()
				.put(TestData.baseUrlActivity + "/abc").then().statusCode(400);
	}

	@Test
	public void testUpdateActivitiesWithNonExistentID() {
		given().header("Content-Type", "application/json").body(validPayload).when()
				.put(TestData.baseUrlActivity + "/9999").then().statusCode(200).body("id", equalTo(23))
				.body("title", equalTo("New1")).body("completed", equalTo(true));
	}

	@Test
	public void testUpdateActivitiesWithSpecialCharacterId() {
		given().header("Content-Type", "application/json").body(validPayload).when()
				.put(TestData.baseUrlActivity + "/!@#").then().statusCode(400);
	}

	@Test
	public void testUpdateActivitiesWithNegativeID() {
		given().header("Content-Type", "application/json").body(validPayload).when()
				.put(TestData.baseUrlActivity + "/- 123").then().log().all().statusCode(400);
	}

	@Test
	public void testUpdateActivitesWithUnexpectedFields() {
		String extraFieldsPayload = """
						{
						 "id": 123,
				         "title": "sujata act",
				         "dueDate": "0001-01-01T00:00:00",
				          "completed": false,
						  "extraField": "Unexpected Value"
						}
						""";

		given().header("Content-Type", "application/json").body(extraFieldsPayload).when()
				.put(TestData.baseUrlActivity + "/123").then().statusCode(200).body("id", equalTo(123))
				.body("title", equalTo("sujata act")).body("dueDate", equalTo("0001-01-01T00:00:00")).body("completed", equalTo(false)).body("extraField", nullValue());
	}
}
