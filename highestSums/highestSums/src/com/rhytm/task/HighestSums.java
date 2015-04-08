package com.rhytm.task;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HighestSums {
	
	/**
	 * Finds the n largest sums amongst all the combinations of picking an integer from each list.
	 * @param lists - Two-dimensional array of int
	 * @param n - Largest sums to be found
	 * @return List of largest sums
	 */
	public List<Integer> findHighestSums (int[][] lists, int n) {
		// convert 2D array
		List<List<Integer>> outerList = arrayToList(lists, n);
		
		// the resulting list of largest sums
		List<Integer> highestSumsList = new ArrayList<Integer>();
		
		// move the first numbers list out of others
		// separated one list, because the lists here are traversed top->bottom, left->right
		// when one element from the list is processed, the tail of list must be skipped 
		// (because must not sum up the element with ones from the same list)
		// having one list separated and its own loop resolves this problem 
		List<Integer> activeList = outerList.get(0);
		outerList.remove(0);
		
		// loop over the separated list 
		for (Integer activeListEl : activeList) {
			// list of sums for a particular element from separated list
			// re-written after going over each innerList
			List<Integer> activeSumList = new ArrayList<Integer>();
			// first element to sum up with other numbers is activeListEl
			activeSumList.add(activeListEl);
			
			// loop over list of outerList (list of List's) 
			for (List<Integer> innerList : outerList) {
				// list to accumulate sums for a particular innerList
				List<Integer> innerSumList = new ArrayList<Integer>();
				
				// loop over innerList (list of Integers)
				for (Integer innerListEl : innerList) {
					
					// loop over the list of current sums
					for (Integer currSum : activeSumList) {						
						
						// add sum to the innerSumList
						innerSumList.add(currSum+innerListEl);
					}
				}
				// clear the activeSumList, since it contains obsolete values
				activeSumList.clear();
				// fill activeSumList with innerSumList elements, since those are up-to-date sums  
				activeSumList.addAll(innerSumList);
				//innerSumList.clear();
			}
			// after finishing with another activeListEl, dump activeSumList to the resulting list
			highestSumsList.addAll(activeSumList);

			// sort and reverse the resulting list
			Collections.sort(highestSumsList);
			Collections.reverse(highestSumsList);
			
			// truncate the unnecessary tail of list
			if (highestSumsList.size() >= n) 
				highestSumsList = highestSumsList.subList(0, n);
		}

		return highestSumsList;
		
	}

	/**
	 * Converts the 2D array to 2D List. Limits the size of List<Integer> to n, 
	 * since the rest of list has no chance to contribute to top-n sums  
	 * @param lists - Two-dimensional array of int
	 * @param n - The maximum length of output List<Integers>
	 * @return Two-dimensional List
	 */
	private List<List<Integer>> arrayToList(int[][] lists, int n) {
		List<List<Integer>> outerList = new ArrayList(new ArrayList<Integer>());
		// go over the array of arrays
		for (int outerArray = 0; outerArray < lists.length; outerArray++) {
		
			List<Integer> temp = new ArrayList<Integer>();
			// go over the array of numbers
			// the upper bound is n, since the elements with index greater than n  
			// have no chance to contribute to the top-n sums   
			for (int innerArray = 0; innerArray < lists[outerArray].length && innerArray < n; innerArray++) {
				temp.add(lists[outerArray][innerArray]);
			}
			outerList.add(temp);
		}
		
		return outerList;
	}
}
