package com.github.huangzhiyi.shpmdir.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 
 * @author huangzhiyi
 *
 */
public class MysqlMngr {

	private final static class SingletonHolder{
		private final static MysqlMngr instance=new MysqlMngr();
	}
	
	private MysqlMngr(){
		
	}
	
	public static MysqlMngr instance(){
		return SingletonHolder.instance;
	}
	
	public Connection getConnection() throws ClassNotFoundException, SQLException{
		Class.forName("com.mysql.jdbc.Driver");
		return DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","v#3smsYc");
	}
	
}
