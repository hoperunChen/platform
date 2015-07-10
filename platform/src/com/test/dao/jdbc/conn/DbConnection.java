package com.test.dao.jdbc.conn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
	public static Connection connMySql() {
		Connection conn = null;
		String url = "jdbc:mysql://localhost:3306/xjxl?user=root&password=123&useUnicode=true&characterEncoding=utf8";
		try {
			Class.forName("org.gjt.mm.mysql.Driver").newInstance();
			conn = DriverManager.getConnection(url);
			
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

}
