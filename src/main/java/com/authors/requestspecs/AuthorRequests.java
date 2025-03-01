package com.authors.requestspecs;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import com.swaggerdata.TestData;

import static io.restassured.RestAssured.*;

public class AuthorRequests {

	private static final String BASE_URL = TestData.baseUrl;
	private static final String ENDPOINT = "Authors/{id}";

	// Common Request Specification for GET requests
	private static RequestSpecification getRequestSpec() {
		return new RequestSpecBuilder().setBaseUri(BASE_URL).setContentType(ContentType.JSON).build();
	}

	// Common Request Specification for POST requests
	private static RequestSpecification postRequestSpec(String payload) {
		return new RequestSpecBuilder().setBaseUri(BASE_URL+ "/Authors").setContentType(ContentType.JSON).setBody(payload).build();
	}

	// Common Request Specification for PUT requests
	private static RequestSpecification putRequestSpec(String payload, String authorId) {
		return new RequestSpecBuilder().setBaseUri(BASE_URL + "/Authors/" + authorId).setContentType(ContentType.JSON)
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

	// POST Requests
	public static Response createAuthor(String payload) {
		return given().spec(postRequestSpec(payload)).when().post();
	}

	// PUT Requests
	public static Response updateAuthor(String authorId, String payload) {
		return given().spec(putRequestSpec(payload, authorId)).when().put();
	}

	// DELETE Requests
	public static Response deleteAuthor(int id) {
		return given().spec(deleteRequestSpec(id)).when().delete(ENDPOINT);
	}

	public static Response deleteAuthor(String id) {
		return given().spec(deleteRequestSpec(id)).when().delete(ENDPOINT);
	}
}