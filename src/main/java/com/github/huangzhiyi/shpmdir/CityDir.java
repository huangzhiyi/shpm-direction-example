package com.github.huangzhiyi.shpmdir;


/**
 * China Mainland city directions.
 * 
 * @author huangzhiyi
 *
 */
public class CityDir {

	private CitySet origCitySet=CitySet.emptySet();
	private CitySet destCitySet=CitySet.emptySet();
	private String name="";
	
	public CityDir orig(CitySet orig){
		this.origCitySet=orig;
		return this;
	}
	
	public CityDir dest(CitySet dest){
		this.destCitySet=dest;
		return this;
	}
	
	public CityDir name(String name){
		this.name=name;
		return this;
	}
	
	public String getName(){
		return name;
	}

	public CitySet getOrigCitySet() {
		return origCitySet;
	}

	public CitySet getDestCitySet() {
		return destCitySet;
	}
}
