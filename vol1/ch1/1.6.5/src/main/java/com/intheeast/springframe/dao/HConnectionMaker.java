package com.intheeast.springframe.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class HConnectionMaker implements ConnectionMaker {
	
	public HConnectionMaker() {}

	@Override
	public Connection makeConnection() throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		//Class.forName("org.h2.Driver"); 자동 로드됨
		// jdbc:h2:tcp://localhost/~/test
		Connection c = DriverManager.getConnection(
				"jdbc:h2:tcp://localhost/~/test",
				"sa",
				"");
		
		return c;
	}

}
