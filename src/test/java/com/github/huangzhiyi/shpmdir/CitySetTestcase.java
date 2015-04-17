package com.github.huangzhiyi.shpmdir;

import static org.junit.Assert.*;

import org.junit.Test;

public class CitySetTestcase {

	@Test
	public void test_toBinStr(){
		assertEquals("1",CitySet.ofCityCodes("1").toBinStr());
		assertEquals("101",CitySet.ofCityCodes("1","3").toBinStr());
		assertEquals("000010101",CitySet.ofCityCodes("5","7","9").toBinStr());
	}
	
	@Test
	public void test_ofBinStr(){
		assertEquals("11",CitySet.ofBinStr("11").toBinStr());
		assertEquals("0101",CitySet.ofBinStr("0101").toBinStr());
		assertEquals("1000010101",CitySet.ofBinStr("1000010101").toBinStr());
	}
	
	@Test
	public void test_contains(){
		assertTrue(CitySet.ofCityCodes("1","3").contains("1","3"));
		assertFalse(CitySet.ofCityCodes("1","3").contains("1","5"));
	}
	
	@Test
	public void test_isEmpty(){
		assertTrue(CitySet.emptySet().isEmpty());
		assertTrue(CitySet.ofCityCodes("1").isNotEmpty());
	}
}
