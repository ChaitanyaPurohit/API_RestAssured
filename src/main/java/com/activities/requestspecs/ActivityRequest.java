package com.activities.requestspecs;

import static io.restassured.RestAssured.given;

import com.swaggerdata.TestData;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ActivityRequest {
	
	private static final String BASE_URL = TestData.baseUrlActivity;
	private static final String ENDPOINT = "/{id}";

	// Common Request Specification for GET requests
	private static RequestSpecification getRequestSpec() {
		return new RequestSpecBuilder().setBaseUri(BASE_URL).setContentType(ContentType.JSON).build();
	}

	// Common Request Specification for POST requests
	private static RequestSpecification postRequestSpec(String payload) {
		return new RequestSpecBuilder().setBaseUri(BASE_URL).setContentType(ContentType.JSON).setBody(payload).build();
	}

	// Common Request Specification for PUT requests
	private static RequestSpecification putRequestSpec(String payload, String activityId) {
		return new RequestSpecBuilder().setBaseUri(BASE_URL + "/" + activityId).setContentType(ContentType.JSON)
				.setBody(payload).build();
	}

	// Common Request Specification for DELETE requests
	private static RequestSpecification deleteRequestSpec(int id) {
		return new RequestSpecBuilder().setBaseUri(BASE_URL).addPathParam("id", id).setContentType(ContentType.JSON).build();
	}

	// Common Request Specification for DELETE requests with non-numeric ID
	private static RequestSpecification deleteRequestSpec(String id) {
		return new RequestSpecBuilder().setBaseUri(BASE_URL).addPathParam("id", id).setContentType(ContentType.JSON).build();
	}

	// GET Requests
	public static Response getAllActivity() {
		return given().spec(getRequestSpec()).when().get();
	}

	public static Response getActivityById(int ActicityId) {
		return given().spec(getRequestSpec()).when().get(""+ ActicityId);
	}

	public static Response getNonExistingActivityById() {
		return given().spec(getRequestSpec()).when().get("/12345");
	}

	public static Response getAcitivitiesWithInvalidIdFormat() {
		return given().spec(getRequestSpec()).when().get("/abc");
	}

	// POST Requests
	public static Response createActivity(String payload) {
		return given().spec(postRequestSpec(payload)).when().post();
	}

	// PUT Requests
	public static Response updateActivity(String activityId, String payload) {
		return given().spec(putRequestSpec(payload, activityId)).when().put();
	}

	// DELETE Requests
	public static Response deleteActivity(int id) {
		return given().spec(deleteRequestSpec(id)).when().delete(ENDPOINT);
	}

	public static Response deleteActivity(String id) {
		return given().spec(deleteRequestSpec(id)).when().delete(ENDPOINT);
	}

}
