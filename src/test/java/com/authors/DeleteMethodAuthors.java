package com.authors;


import org.testng.annotations.Test;

import com.swaggerdata.TestData;

import static io.restassured.RestAssured.given;

public class DeleteMethodAuthors {

	@Test
	public void testDeleteExistingAuthor() {
		int authorId = 5;

		given().pathParam("id", authorId).when().delete(TestData.baseUrl + "/{id}").then().statusCode(200);
	}

	@Test
	public void testDeleteNonExistingAuthor() {
		int invalidAuthorId = 9999;

		given().pathParam("id", invalidAuthorId).when().delete(TestData.baseUrl + "/{id}").then().statusCode(404);
	}

	@Test
	public void testDeleteWithInvalidId() {
		int invalidId = -1;

		given().pathParam("id", invalidId).when().delete(TestData.baseUrl + "/{id}").then().statusCode(400);
	}

	@Test
	public void testDeleteWithNonNumericId() {
		given().pathParam("id", "abc").when().delete(TestData.baseUrl + "/{id}").then().statusCode(400);
	}

	@Test
	public void testAuthorIsDeleted() {
		int authorId = 7;

		given().pathParam("id", authorId).when().delete(TestData.baseUrl + "/{id}").then().statusCode(200);

		given().pathParam("id", authorId).when().get(TestData.baseUrl + "/{id}").then().statusCode(404);
	}
}
