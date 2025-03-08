package com.ActivitiesROM;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.nullValue;

import org.testng.annotations.Test;

import com.activities.requestspecs.ActivityRequest;

public class PostMethodActivitiesUsingRom{

	String validPayload = "{ \"id\": 123, \"title\": \"NewActivity\", \"dueDate\": \"2025-02-24T18:52:27.658Z\", \"completed\": true}";

	@Test
	public void testCreateActivitySuccess() {
		ActivityRequest.createActivity(validPayload).then().statusCode(200).contentType("application/json")
				.body("title", equalTo("NewActivity")).body("completed", equalTo(true));
	}

	@Test
	public void testCreateActivityMissingCompleted() {
		String payloadWithoutCompleted = "{ \"id\": 123, \"title\": \"NewActivity\", \"dueDate\": \"2025-02-24T18:52:27.658Z\"}";

		ActivityRequest.createActivity(payloadWithoutCompleted).then().statusCode(200).body("completed", equalTo(false));
	}

	@Test
	public void testCreateActivityDuplicateID() {
		ActivityRequest.createActivity(validPayload).then().statusCode(200);

		ActivityRequest.createActivity(validPayload).then().statusCode(200).body("id", equalTo(123));
	}

	@Test
	public void testCreateActivity_EmptyPayload() {
		ActivityRequest.createActivity("{}").then().statusCode(200).body("id", equalTo(0))
				.body("title", nullValue()).body("completed",equalTo(false));
	}

	

	@Test
	public void testCreateActivity_ExtraFields() {
		String payloadWithExtraFields = "{ \"id\": 123, \"title\": \"NewActivity\", \"dueDate\": \"2025-02-24T18:52:27.658Z\", \"completed\": false, \"extraField\": \"extraValue\" }";

		ActivityRequest.createActivity(payloadWithExtraFields).then().statusCode(200).body("extraField", nullValue());
	}

	

}
