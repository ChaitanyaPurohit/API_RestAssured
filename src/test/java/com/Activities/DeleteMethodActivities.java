package com.Activities;

import static io.restassured.RestAssured.given;

import static org.hamcrest.Matchers.lessThan;

import org.testng.annotations.Test;

import com.restAssured.base.BaseTest;
import com.swaggerdata.TestData;



public class DeleteMethodActivities{

	@Test
	public void deleteExistingActivity() {
	    // Assuming activity ID 1 exists
	    int activityId = 1;
	    given().when().delete(TestData.baseUrlActivities + activityId).then()
		.log()
		.all()
		.assertThat()
		.statusCode(200);
	   
	}
	
	@Test
	public void deleteNonExistingActivity() {
	    
	    int activityId = 99999;

	    given().when().delete(TestData.baseUrlActivities + activityId) .then()
	    .log()
		.all()
		.assertThat()
	    .statusCode(200);
	    
	}
	
	@Test
	public void deleteActivityWithInvalidId() {
	    
	    String activityId = "invalid_id";

	    given().when().delete(TestData.baseUrlActivities + activityId).then()
	    .log()
		.all()
		.assertThat()
	    .statusCode(400); // Bad Request
	       
	}

	@Test
	public void deleteActivityWithoutId() {
	    given().when().delete(TestData.baseUrlActivities).then()
	    .log()
		.all()
		.assertThat()
	    .statusCode(405); // Bad Request
	       

	}
	@Test
	public void checkResponseTime() {
		int validId=1;
		 given().when().delete(TestData.baseUrlActivities + validId).then()
		    .log()
			.all()
			.assertThat()
		    .statusCode(200)
		    .time(lessThan(5289L));
	}
	@Test
	
	public void deletewithLargeId() {
	   

	    long activityId = 999999999999999L;

	    given().when().delete(TestData.baseUrlActivities + activityId) .then()
	    .log()
		.all()
		.assertThat()
	    .statusCode(400);
	        
	}


}
