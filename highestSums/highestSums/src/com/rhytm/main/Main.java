package com.rhytm.main;

import org.testng.TestListenerAdapter;
import org.testng.TestNG;

import com.rhytm.task.HighestSumsTest;

public class Main {

	/*
	 * Method runs test cases from HighestSumsTest class 
	 */
	public static void main(String[] args) {
		TestListenerAdapter tla = new TestListenerAdapter();
		TestNG testng = new TestNG();
		testng.setTestClasses(new Class[] { HighestSumsTest.class });
		testng.addListener(tla);
		testng.run(); 
	}

}
