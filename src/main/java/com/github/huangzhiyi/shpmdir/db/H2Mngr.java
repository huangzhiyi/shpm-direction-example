package com.github.huangzhiyi.shpmdir.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class H2Mngr implements DBMngr{

	private final static class SingletonHolder{
		private final static H2Mngr instance=new H2Mngr();
	}
	
	private H2Mngr(){
		
	}
	
	public static H2Mngr instance(){
		return SingletonHolder.instance;
	}
	
	@Override
	public Connection getConnection() throws ClassNotFoundException,
			SQLException {
		Class.forName("org.h2.Driver");
	    return DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
	}

}
