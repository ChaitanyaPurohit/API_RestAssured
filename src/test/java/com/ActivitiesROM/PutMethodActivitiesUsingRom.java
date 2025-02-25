package com.ActivitiesROM;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.nullValue;

import org.testng.annotations.Test;

import com.activities.requestspecs.ActivityRequest;
import com.authors.requestspecs.AuthorRequests;

public class PutMethodActivitiesUsingRom {

	String validPayload = "{ \"id\": 23, \"title\": \"NewActivity\", \"dueDate\": \"2025-02-24T18:52:27.658Z\", \"completed\": true}";

	@Test
	public void testUpdateActivityWithValidPayload() {
		ActivityRequest.updateActivity("123", validPayload).then().statusCode(200).body("id", equalTo(23))
		.body("title", equalTo("NewActivity")).body("completed", equalTo(true));
	}

	@Test
	public void testUpdateActivityWithEmptyPayload() {
		ActivityRequest.updateActivity("123", "{}").then().statusCode(200).body("id", equalTo(0))
		.body("title", equalTo(null)).body("completed", equalTo(false));
	}

	@Test
	public void testUpdateActivityWithMissingFields() {
		String incompletePayload = """
				{
				  "id": 123
				}
				""";

		ActivityRequest.updateActivity("123", incompletePayload).then().statusCode(200).body("id", equalTo(123))
				.body("title", equalTo(null));
	}

	@Test
	public void testUpdateActivityWithInvalidIDFormat() {
		ActivityRequest.updateActivity("abc", validPayload).then().statusCode(400);
	}

	@Test
	public void testUpdateActivityWithNonExistentID() {
		ActivityRequest.updateActivity("9999", validPayload).then().statusCode(200).body("id", equalTo(23));
	}

	@Test
	public void testUpdateActivityWithSpecialCharacterID() {
		AuthorRequests.updateAuthor("!@#", validPayload).then().statusCode(400);
	}

	@Test
	public void testUpdateActivityWithNegativeID() {
		AuthorRequests.updateAuthor("-123", validPayload).then().log().all().
		statusCode(200);
	}

	
}
