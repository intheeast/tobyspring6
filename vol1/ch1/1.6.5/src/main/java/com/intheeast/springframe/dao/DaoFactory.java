package com.intheeast.springframe.dao;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// annotation based configuration metadata
@Configuration
public class DaoFactory {

	@Bean
	public UserDao userDao() {
		UserDao dao = new UserDao();
		// dependency injection
		// : method injection
		dao.setConnectionMaker(connectionHMaker());
		return dao;
				
	}
	
	@Bean
	public ConnectionMaker connectionMMaker() {
		ConnectionMaker connectionMaker = 
				new MConnectionMaker();
		return connectionMaker;
	}
	
	@Bean
	public ConnectionMaker connectionHMaker() {
		ConnectionMaker connectionMaker = 
				new HConnectionMaker();
		return connectionMaker;
	}
}
