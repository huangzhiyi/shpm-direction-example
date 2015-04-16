package com.github.huangzhiyi.shpmdir;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CitySetTestcase {

	/**
	 * Test getByteArr() method
	 */
	@Test
	public void test_toBinStr(){
		assertEquals("11",CitySet.ofCityCodes("1").toBinStr());
		assertEquals("1101",CitySet.ofCityCodes("1","3").toBinStr());
		assertEquals("1000010101",CitySet.ofCityCodes("5","7","9").toBinStr());
	}
}
