package com.authorsrom;


import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.response.Response;
import com.authors.requestspecs.AuthorRequests;
import com.restAssured.base.BaseTest;

public class DeleteMethodAuthorsUsingRom{

    @Test
    public void testDeleteExistingAuthor() {
        int authorId = 5;

        Response response = AuthorRequests.deleteAuthor(authorId);
        Assert.assertEquals(response.getStatusCode(), 200, "Expected status code 200 for successful deletion.");
    }

    @Test
    public void testDeleteNonExistingAuthor() {
        int invalidAuthorId = 9999;

        Response response = AuthorRequests.deleteAuthor(invalidAuthorId);
        Assert.assertEquals(response.getStatusCode(), 404, "Expected status code 404 for non-existing author.");
    }

    @Test
    public void testDeleteWithInvalidId() {
        int invalidId = -1;

        Response response = AuthorRequests.deleteAuthor(invalidId);
        Assert.assertEquals(response.getStatusCode(), 400, "Expected status code 400 for invalid ID.");
    }

    @Test
    public void testDeleteWithNonNumericId() {
        Response response = AuthorRequests.deleteAuthor("abc");
        Assert.assertEquals(response.getStatusCode(), 400, "Expected status code 400 for non-numeric ID.");
    }

    @Test
    public void testAuthorIsDeleted() {
        int authorId = 7;

        // Step 1: Delete the author
        Response deleteResponse = AuthorRequests.deleteAuthor(authorId);
        Assert.assertEquals(deleteResponse.getStatusCode(), 200, "Expected status code 200 for successful deletion.");

        // Step 2: Verify the author no longer exists
        Response getResponse = AuthorRequests.getAuthorById(authorId);
        Assert.assertEquals(getResponse.getStatusCode(), 404, "Expected status code 404 after deletion.");
    }
}