package com.ActivitiesROM;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.activities.requestspecs.ActivityRequest;
import com.restAssured.base.BaseTest;

import io.restassured.response.Response;

public class DeleteMethodActivitiesUsingRom{
	
	@Test
    public void testDeleteExistingActivity() {
        int activityId = 6;

        Response response = ActivityRequest.deleteActivity(activityId);
        Assert.assertEquals(response.getStatusCode(), 200, "Expected status code 200 for successful deletion.");
    }

    @Test
    public void testDeleteNonExistingActivity() {
        int invalidActivityId = 5555;

        Response response = ActivityRequest.deleteActivity(invalidActivityId);
        Assert.assertEquals(response.getStatusCode(), 404, "Expected status code 404 for non-existing activity.");
    }

    @Test
    public void testDeleteWithinvalidActivityId() {
        int invalidActivityId = -1;

        Response response = ActivityRequest.deleteActivity(invalidActivityId);
        Assert.assertEquals(response.getStatusCode(), 400, "Expected status code 400 for invalid ID.");
    }

    @Test
    public void testDeleteWithNonNumericId() {
        Response response = ActivityRequest.deleteActivity("abc");
        Assert.assertEquals(response.getStatusCode(), 400, "Expected status code 400 for non-numeric ID.");
    }

    @Test
    public void testActivityIsDeleted() {
        int ActivityId = 6;

        
        Response deleteResponse = ActivityRequest.deleteActivity(ActivityId);
        Assert.assertEquals(deleteResponse.getStatusCode(), 200, "Expected status code 200 for successful deletion.");

       
        Response getResponse = ActivityRequest.getActivityById(ActivityId);
        Assert.assertEquals(getResponse.getStatusCode(), 404, "Expected status code 404 after deletion.");
    }

}
