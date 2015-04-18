package com.github.huangzhiyi.shpmdir.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.github.huangzhiyi.shpmdir.CityDir;
import com.github.huangzhiyi.shpmdir.CitySet;


public class CityDirDao {

	private static final DBMngr DBMNGR=H2Mngr.instance();
	
	public static void createTable(){
		try{
			Connection conn=DBMNGR.getConnection();
			PreparedStatement stat=conn.prepareStatement("CREATE TABLE IF NOT EXISTS citydir(name varchar(128),orig varchar(1000),dest varchar(1000))");
			stat.execute();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * Save a city direction
	 * @param cityDir
	 */
	public static void save(CityDir cityDir){
		try{
			Connection conn=DBMNGR.getConnection();
			PreparedStatement stat=conn.prepareStatement("insert into citydir (name,orig,dest) values(?,?,?)");
			
			stat.setString(1, cityDir.getName());
			stat.setString(2, cityDir.getOrigCitySet().toBinStr());
			stat.setString(3, cityDir.getDestCitySet().toBinStr());
			
			stat.execute();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * Find first city direction by name
	 * 
	 * @param name
	 * @return
	 */
	public static CityDir findFirstByName(String name){
		CityDir result=null;
		try{
			Connection conn=DBMNGR.getConnection();
			PreparedStatement stat=conn.prepareStatement("select name,orig,dest from citydir where name=?");
			
			stat.setString(1, name);
			
			ResultSet rs=stat.executeQuery();
			if(rs.next()){
				result=new CityDir().name(name)
						.orig(CitySet.ofBinStr(rs.getString("orig")))
						.dest(CitySet.ofBinStr(rs.getString("dest")));
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * Find by original city set or destination city set.
	 * @param orig
	 * @param dest
	 * @return
	 */
	public static List<CityDir> findByCitySet(CitySet orig,CitySet dest){
		List<CityDir> result=new LinkedList<>();
		List<Integer> param=new ArrayList<>();
		String sql="select name,orig,dest from citydir";
		if(orig.isNotEmpty() || dest.isNotEmpty()){
			sql+=" where 1=1 ";
			if(orig.isNotEmpty()){
				for(int i:orig.toIntArray()){
					sql+=" and substr(orig,?,1)='1' ";
					param.add(i);
				}
			}
			if(dest.isNotEmpty()){
				for(int i:dest.toIntArray()){
					sql+=" and substr(dest,?,1)='1' ";
					param.add(i);
				}
			}
		}
		try{
			Connection conn=DBMNGR.getConnection();
			PreparedStatement stat=conn.prepareStatement(sql);
			
			for(int i=0;i<param.size();i++){
				stat.setInt(i+1, param.get(i));
			}
			
			ResultSet rs=stat.executeQuery();
			while(rs.next()){
				result.add(new CityDir().name(rs.getString("name"))
						.orig(CitySet.ofBinStr(rs.getString("orig")))
						.dest(CitySet.ofBinStr(rs.getString("dest"))));
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * Delete all the data in table 'citydir'
	 */
	public static void clearAllData(){
		try{
			Connection conn=DBMNGR.getConnection();
			PreparedStatement stat=conn.prepareStatement("truncate table citydir");
			stat.execute();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
}
