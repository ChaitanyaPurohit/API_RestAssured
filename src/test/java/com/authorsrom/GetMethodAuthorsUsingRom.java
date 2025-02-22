package com.authorsrom;

import org.testng.annotations.Test;

import com.authors.requestspecs.AuthorRequests;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.equalTo;

public class GetMethodAuthorsUsingRom {

	@Test
	public void testGetAllAuthors() {
		AuthorRequests.getAllAuthors().then().statusCode(200).body("size()", greaterThan(0)).log().all();
	}

	@Test
	public void testGetAuthorsByBookId() {
		AuthorRequests.getAuthorsByBookId(1).then().log().all().statusCode(200).body("size()", greaterThan(0));
	}

	@Test
	public void testGetAuthorById() {
		AuthorRequests.getAuthorById(2).then().statusCode(200).body("id", equalTo(2));
	}

	@Test
	public void testGetNonExistingAuthorById() {
		AuthorRequests.getNonExistingAuthorById().then().statusCode(404);
	}

	@Test
	public void testGetAuthorsWithInvalidIdFormat() {
		AuthorRequests.getAuthorsWithInvalidIdFormat().then().statusCode(400);
	}

}
