package org.enjket.panda.whitevault.utils;

import static org.junit.Assert.*;

import org.enjket.panda.whitevault.utils.RandomNumberPumps;
import org.junit.Test;

public class RandomNumberPumpsTest {

	@Test
	public void padStringTest() {
		String testStr;
		for(int i=0;i<400;i++)
		{
			testStr=RandomNumberPumps.getPadStr();
			assertNotNull(testStr);
			assertEquals(testStr.length(),16);
			//Ensures no leading zeros and sufficiently large
			//pad
			assertFalse(testStr.contains("0"));
		}
	}


	@Test
	public void sixDigitStringTest() {
		String testStr;
		for(int i=0;i<400;i++)
		{
			testStr=RandomNumberPumps.getSixDigitStr();
			assertNotNull(testStr);
			assertEquals(testStr.length(),6);
			//Doesn't contain zeros as it  is the same
			//random number pump as the 
			assertFalse(testStr.contains("0"));
		}
	}
}
