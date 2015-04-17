package com.github.huangzhiyi.shpmdir.db;

import static org.junit.Assert.*;

import org.junit.Test;

import com.github.huangzhiyi.shpmdir.CityDir;
import com.github.huangzhiyi.shpmdir.CitySet;

public class CityDirDaoTestcase {

	@Test
	public void test_save(){
		CityDirDao.save(new CityDir().name("test")
				.from(CitySet.ofCityCodes("755","020"))
				.to(CitySet.ofCityCodes("759","010")));
		
		CityDir cd=CityDirDao.findByName("test");
		assertTrue(cd.getFromCitySet().contains("755","020"));
		assertTrue(cd.getToCitySet().contains("759","010"));
	}
}
