package com.authors.requestspecs;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import com.swaggerdata.TestData;
import static io.restassured.RestAssured.*;

public class RequestSpecsForGet {

	private static final String BASE_URL = TestData.baseUrl;

	private static RequestSpecification getRequestSpec() {
		return new RequestSpecBuilder().setBaseUri(BASE_URL).setContentType(ContentType.JSON).build();
	}

	public static Response getAllAuthors() {
		return given().spec(getRequestSpec()).when().get();
	}

	public static Response getAuthorsByBookId(int bookId) {
		return given().spec(getRequestSpec()).when().get("/books/" + bookId);
	}

	public static Response getAuthorById(int authorId) {
		return given().spec(getRequestSpec()).when().get("/" + authorId);
	}

	public static Response getNonExistingAuthorById() {
		return given().spec(getRequestSpec()).when().get("/12345");
	}

	public static Response getAuthorsWithInvalidIdFormat() {
		return given().spec(getRequestSpec()).when().get("/abc");
	}
}
