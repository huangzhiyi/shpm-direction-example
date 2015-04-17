package com.github.huangzhiyi.shpmdir;


/**
 * China Mainland city directions.
 * 
 * @author huangzhiyi
 *
 */
public class CityDir {

	private CitySet fromCitySet=CitySet.emptySet();
	private CitySet toCitySet=CitySet.emptySet();
	private String name="";
	
	public CityDir from(CitySet from){
		this.fromCitySet=from;
		return this;
	}
	
	public CityDir to(CitySet to){
		this.toCitySet=to;
		return this;
	}
	
	public CityDir name(String name){
		this.name=name;
		return this;
	}
	
	public String getName(){
		return name;
	}

	public CitySet getFromCitySet() {
		return fromCitySet;
	}

	public CitySet getToCitySet() {
		return toCitySet;
	}
}
