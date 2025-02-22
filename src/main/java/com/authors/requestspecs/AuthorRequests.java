package com.authors.requestspecs;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import com.swaggerdata.TestData;

import static io.restassured.RestAssured.*;

public class AuthorRequests {

    private static final String BASE_URL = TestData.baseUrl;

    // Common Request Specification for GET requests
    private static RequestSpecification getRequestSpec() {
        return new RequestSpecBuilder()
                .setBaseUri(BASE_URL)
                .setContentType(ContentType.JSON)
                .build();
    }

    // Common Request Specification for POST requests
    private static RequestSpecification postRequestSpec(String payload) {
        return new RequestSpecBuilder()
                .setBaseUri(BASE_URL)
                .setContentType(ContentType.JSON)
                .setBody(payload)
                .build();
    }

    // Common Request Specification for PUT requests
    private static RequestSpecification putRequestSpec(String payload, String authorId) {
        return new RequestSpecBuilder()
                .setBaseUri(BASE_URL + "/" + authorId)
                .setContentType(ContentType.JSON)
                .setBody(payload)
                .build();
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
}
