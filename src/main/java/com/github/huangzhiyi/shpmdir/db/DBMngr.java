package com.github.huangzhiyi.shpmdir.db;

import java.sql.Connection;
import java.sql.SQLException;

public interface DBMngr {

	Connection getConnection() throws ClassNotFoundException, SQLException;
}
