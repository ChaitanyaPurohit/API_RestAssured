package com.restAssured.base;



import org.testng.TestNG;
import org.testng.annotations.Test;


public class BaseTest{
	@Test
	public void testsRunner() {
		TestNG testng = new TestNG();
      testng.setTestClasses(new Class[]{
          com.ActivitiesROM.DeleteMethodActivitiesUsingRom.class,
          com.ActivitiesROM.GetMethodActivitiesUsingRom.class,
          com.ActivitiesROM.PostMethodActivitiesUsingRom.class,
          com.ActivitiesROM.PutMethodActivitiesUsingRom.class,
          com.authors.DeleteMethodAuthors.class,
          com.authors.GetMethodAuthors.class,
          com.authors.PostMethodAuthors.class,
          com.authors.PutMethodAuthors.class,
          com.authorsrom.DeleteMethodAuthorsUsingRom.class,
          com.authorsrom.GetMethodAuthorsUsingRom.class,
          com.authorsrom.PostMethodAuthorsUsingRom.class,
          com.authorsrom.PutMethodAuthorsUsingRom.class
      });

      testng.run();
		

	}
//    public static void main(String[] args) {
//        TestNG testng = new TestNG();
//        testng.setTestClasses(new Class[]{
//            com.ActivitiesROM.DeleteMethodActivitiesUsingRom.class,
//            com.ActivitiesROM.GetMethodActivitiesUsingRom.class,
//            com.ActivitiesROM.PostMethodActivitiesUsingRom.class,
//            com.ActivitiesROM.PutMethodActivitiesUsingRom.class,
//            com.authors.DeleteMethodAuthors.class,
//            com.authors.GetMethodAuthors.class,
//            com.authors.PostMethodAuthors.class,
//            com.authors.PutMethodAuthors.class,
//            com.authorsrom.DeleteMethodAuthorsUsingRom.class,
//            com.authorsrom.GetMethodAuthorsUsingRom.class,
//            com.authorsrom.PostMethodAuthorsUsingRom.class,
//            com.authorsrom.PutMethodAuthorsUsingRom.class
//        });
//
//        testng.run();
//    }
}