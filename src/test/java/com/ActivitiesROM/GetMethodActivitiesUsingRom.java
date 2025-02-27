package com.ActivitiesROM;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;

import org.testng.annotations.Test;

import com.activities.requestspecs.ActivityRequest;


public class GetMethodActivitiesUsingRom {
	@Test
	public void testGetAllActivities() {
		ActivityRequest.getAllActivity().then().statusCode(200).body("size()", greaterThan(0)).log().all();
	}

	@Test
	public void testGetActivitiesByActivityId() {
		ActivityRequest.getActivityById(1).then().log().all().statusCode(200).body("size()", greaterThan(0));
	}

	@Test
	public void testGetActivityById() {
		ActivityRequest.getActivityById(2).then().statusCode(200).body("id", equalTo(2));
	}

	@Test
	public void testGetNonExistingActivityById() {
		ActivityRequest.getNonExistingActivityById().then().statusCode(404);
	}

	@Test
	public void testGetAuthorsWithInvalidIdFormat() {
		ActivityRequest.getAcitivitiesWithInvalidIdFormat().then().statusCode(400);
	}

}
