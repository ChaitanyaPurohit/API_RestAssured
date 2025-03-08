package com.authorsrom;

import org.testng.annotations.Test;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.nullValue;
import com.authors.requestspecs.AuthorRequests;

public class PutMethodAuthorsUsingRom{
	String validPayload = "{ \"id\": 123, \"idBook\": 789, \"firstName\": \"Chaitanya\", \"lastName\": \"Purohit\"}";

	@Test
	public void testUpdateAuthorWithValidPayload() {
		AuthorRequests.updateAuthor("123", validPayload).then().statusCode(200).body("id", equalTo(123))
				.body("idBook", equalTo(789)).body("firstName", equalTo("Chaitanya"))
				.body("lastName", equalTo("Purohit"));
	}

	@Test
	public void testUpdateAuthorWithEmptyPayload() {
		AuthorRequests.updateAuthor("123", "{}").then().statusCode(200).body("id", equalTo(0)).body("idBook", equalTo(0))
				.body("firstName", nullValue()).body("lastName", nullValue());
	}

	@Test
	public void testUpdateAuthorWithMissingFields() {
		String incompletePayload = """
				{
				  "id": 123
				}
				""";

		AuthorRequests.updateAuthor("123", incompletePayload).then().statusCode(200).body("id", equalTo(123))
				.body("idBook", equalTo(0)).body("firstName", nullValue()).body("lastName", nullValue());
	}

	@Test
	public void testUpdateAuthorWithInvalidIDFormat() {
		AuthorRequests.updateAuthor("abc", validPayload).then().statusCode(400);
	}

	@Test
	public void testUpdateAuthorWithNonExistentID() {
		AuthorRequests.updateAuthor("9999", validPayload).then().statusCode(200).body("id", equalTo(123))
				.body("idBook", equalTo(789)).body("firstName", equalTo("Chaitanya"))
				.body("lastName", equalTo("Purohit"));
	}

	@Test
	public void testUpdateAuthorWithSpecialCharacterID() {
		AuthorRequests.updateAuthor("!@#", validPayload).then().statusCode(400);
	}

	@Test
	public void testUpdateAuthorWithNegativeID() {
		AuthorRequests.updateAuthor("-123", validPayload).then().log().all().
		statusCode(200);
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

		AuthorRequests.updateAuthor("123", extraFieldsPayload).then().statusCode(200).body("id", equalTo(123))
				.body("idBook", equalTo(789)).body("firstName", equalTo("Chaitanya"))
				.body("lastName", equalTo("Purohit"));
	}
}
