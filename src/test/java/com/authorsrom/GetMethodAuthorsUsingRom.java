package com.authorsrom;

import org.testng.annotations.Test;

import com.authors.requestspecs.RequestSpecsForGet;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.equalTo;

public class GetMethodAuthorsUsingRom {

	@Test
	public void testGetAllAuthors() {
		RequestSpecsForGet.getAllAuthors().then().statusCode(200).body("size()", greaterThan(0)).log().all();
	}

	@Test
	public void testGetAuthorsByBookId() {
		RequestSpecsForGet.getAuthorsByBookId(1).then().log().all().statusCode(200).body("size()", greaterThan(0));
	}

	@Test
	public void testGetAuthorById() {
		RequestSpecsForGet.getAuthorById(1).then().statusCode(200).body("id", equalTo(1));
	}

	@Test
	public void testGetNonExistingAuthorById() {
		RequestSpecsForGet.getNonExistingAuthorById().then().statusCode(404);
	}

	@Test
	public void testGetAuthorsWithInvalidIdFormat() {
		RequestSpecsForGet.getAuthorsWithInvalidIdFormat().then().statusCode(400);
	}

}
