package com.github.huangzhiyi.shpmdir.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.github.huangzhiyi.shpmdir.CityDir;
import com.github.huangzhiyi.shpmdir.CitySet;


public class CityDirDao {

	public static void save(CityDir cityDir){
		try{
			Connection conn=MysqlMngr.instance().getConnection();
			PreparedStatement stat=conn.prepareStatement("insert into citydir (name,orig,dest) value(?,?,?)");
			
			stat.setString(1, cityDir.getName());
			stat.setString(2, cityDir.getFromCitySet().toBinStr());
			stat.setString(3, cityDir.getToCitySet().toBinStr());
			
			stat.execute();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static CityDir findByName(String name){
		CityDir result=null;
		try{
			Connection conn=MysqlMngr.instance().getConnection();
			PreparedStatement stat=conn.prepareStatement("select name,orig,dest from citydir where name=?");
			
			stat.setString(1, name);
			
			ResultSet rs=stat.executeQuery();
			if(rs.next()){
				result=new CityDir().name(name)
						.from(CitySet.ofBinStr(rs.getString("orig")))
						.to(CitySet.ofBinStr(rs.getString("dest")));
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
}
