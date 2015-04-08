package com.rhytm.task;

import java.util.Arrays;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class HighestSumsTest {
	// Test data 
	@DataProvider(name = "highestSumTestData")
	public Object[][] createData() {
		
	 int[][] lists1 =  { 
				{5, 4, 3, 2, 1}, 
				{4, 1}, 
				{5, 0, 0}, 
				{6, 4, 2}, 
				{1} 
		};
	 
	 int[][] lists2 =  { 
				{3}, 
				{4, 1}, 
				{15, 10, 0}, 
				{6, 4, 2}, 
				{0} 
		};
	 
	 int[][] lists3 =  { 
				{1}, 
				{1}, 
				{15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 0}, 
				{1}, 
				{0} 
		};

	return new Object[][] {
	   { lists1, 5, Arrays.asList(21, 20, 19, 19, 18) },
	   { lists1, 0, Arrays.asList() },
	   { lists1, 1, Arrays.asList(21) },
	   { lists1, 6, Arrays.asList(21, 20, 19, 19, 18, 18) },
	   { lists2, 5, Arrays.asList(28, 26, 25, 24, 23) },
	   { lists3, 15, Arrays.asList(18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 3) }, // there are 15 sums to find, but only 14 are possible
	 };
	}
	

	/*
	 * Test data resides in createData() method
	 * lists - arrays or arrays with int
	 * n - count of largest sums to find
	 * expected - List of expected largest sums  
	 */
	@Test(dataProvider = "highestSumTestData")
	public void testReturnsExpectedHighestSums(int[][] lists, int n, List<Integer> expected) {
		// given
		HighestSums hs = new HighestSums();
		
		// when
		List<Integer> actual = hs.findHighestSums(lists, n);
				
		// then
		Assert.assertEquals(actual, expected);
	}
	
}
