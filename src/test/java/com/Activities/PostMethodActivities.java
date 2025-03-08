package com.Activities;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.nullValue;

import org.testng.annotations.Test;

import com.swaggerdata.TestData;

import io.restassured.http.ContentType;

import io.restassured.response.Response;

public class PostMethodActivities{
	
	String validPayload = "{\"id\":57,\"title\":\"New1\",\"completed\":true}";
	
	@Test
	public void testCreateValidActivity() {
		Response response=given().contentType(ContentType.JSON).body(validPayload).when().post(TestData.baseUrlActivity);
		System.out.println("Response :"+response.getBody().asString());
		System.out.println("Status code :"+response.getStatusCode());
		System.out.println(response.getBody().asString());
		response.then().statusCode(200)
				.body("title", equalTo("New1"))
				.body("completed", equalTo(true));
	}

	@Test
	public void testCreateActivitiesWithMinimumField() {
		String payloadWithMinFileld = "{\"title\":\"Quick\"}";

		given().contentType(ContentType.JSON).body(payloadWithMinFileld).when().post(TestData.baseUrlActivity).then()
				.statusCode(200).body("title", equalTo("Quick"));

	}

	@Test
	public void testCreateActivitiesWithDuplicateId() {
		given().contentType(ContentType.JSON).body(validPayload).when().post(TestData.baseUrlActivity).then().statusCode(200);

		given().contentType(ContentType.JSON).body(validPayload).when().post(TestData.baseUrlActivity).then().statusCode(200)
				.body("id", equalTo(57));
	}

	@Test
	public void testCreateActivitiesEmptyPayload() {
		given().contentType(ContentType.JSON).body("{}").when().post(TestData.baseUrlActivity).then().statusCode(200)
				.body("id", equalTo(0)).body("title",equalTo(null)).body("completed",equalTo(false));
	}

	

	@Test
	public void testCreateActivitiesExtraFields() {
		String payloadWithExtraFields = "{\"id\":57,\"title\":\"New1\",\"completed\":true , \"extraField\": \"extraValue\" }";
	
		given().contentType(ContentType.JSON).body(payloadWithExtraFields).when().post(TestData.baseUrlActivity).then()
				.statusCode(200).body("extraField", nullValue());
	}

	@Test
	public void testCreateActivitiesBooleanCompleted() {
		String payloadWithNullFields = "{\"title\":\"task\",\"completed\":false }";

		given().contentType(ContentType.JSON).body(payloadWithNullFields).when().post(TestData.baseUrlActivity).then()
				.statusCode(200)
				.body("completed",equalTo(false));
				
	}
	

	


}
