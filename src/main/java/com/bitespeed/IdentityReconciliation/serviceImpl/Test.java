package com.bitespeed.IdentityReconciliation.serviceImpl;

import java.util.Arrays;
import java.util.List;

class Test {
	
	public static void main(String args[]) {

		List<Integer> myList = Arrays.asList(10,15,8,49,25,98,98,32,15);
		
		List<Integer> result = myList.stream().sorted().toList();
		
		System.out.print(result);
	}
	
}
