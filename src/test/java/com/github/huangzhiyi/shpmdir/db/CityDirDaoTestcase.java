package com.github.huangzhiyi.shpmdir.db;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.github.huangzhiyi.shpmdir.CityDir;
import com.github.huangzhiyi.shpmdir.CitySet;

public class CityDirDaoTestcase {

	@Before
	public void createTable(){
		CityDirDao.createTable();
	}
	
	@Test
	public void test_save(){
		CityDirDao.clearAllData();
		CityDirDao.save(new CityDir().name("test")
				.orig(CitySet.ofCityCodes("755","020"))
				.dest(CitySet.ofCityCodes("759","010")));
		
		CityDir cd=CityDirDao.findFirstByName("test");
		assertTrue(cd.getOrigCitySet().contains("755","020"));
		assertTrue(cd.getDestCitySet().contains("759","010"));
	}
	
	@Test
	public void test_findByCitySet(){
		CityDirDao.clearAllData();
		CityDirDao.save(new CityDir().name("SH-TJ")
				.orig(CitySet.ofCityCodes("021"))
				.dest(CitySet.ofCityCodes("022")));
		CityDirDao.save(new CityDir().name("SH-BJ|GZ")
				.orig(CitySet.ofCityCodes("021"))
				.dest(CitySet.ofCityCodes("010","020")));
		
		assertEquals("SH-TJ",CityDirDao.findByCitySet(CitySet.ofCityCodes("021"), CitySet.ofCityCodes("022"))
				.get(0).getName());
		assertEquals("SH-BJ|GZ",CityDirDao.findByCitySet(CitySet.emptySet(), CitySet.ofCityCodes("010","020"))
				.get(0).getName());
		assertEquals(2, CityDirDao.findByCitySet(CitySet.ofCityCodes("021"),CitySet.emptySet()).size());
	}
}
