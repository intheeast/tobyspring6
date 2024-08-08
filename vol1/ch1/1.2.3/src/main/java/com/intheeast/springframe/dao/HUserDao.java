package com.intheeast.springframe.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class HUserDao extends UserDao {

	@Override
	protected Connection getConnection() throws ClassNotFoundException, SQLException {
		// org.h2.Driver.class
		//Class.forName("org.h2.Driver"); 자동 로드됨
		// jdbc:h2:tcp://localhost/~/test
		Connection c = DriverManager.getConnection(
				"jdbc:h2:tcp://localhost/~/test",
				"sa",
				"");
		
		return c;
	}

}
