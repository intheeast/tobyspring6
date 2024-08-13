package com.intheeast.springframe.daotest;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import com.intheeast.springframe.dao.UserDao;
import com.intheeast.springframe.dao.UserDaoJdbc;
import com.intheeast.springframe.dao.UserDaoSql;

@Configuration
public class TestDaoFactory {
	
	@Bean
	public DataSource dataSource() {
		
		SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
		
		dataSource.setDriverClass(com.mysql.cj.jdbc.Driver.class);
		dataSource.setUrl("jdbc:mysql://localhost:3306/sbdt_db?characterEncoding=UTF-8");
		dataSource.setUsername("root");
		dataSource.setPassword("1234");

		return dataSource;
	}

	@Bean
	public UserDao userDao() {
		UserDao userDao = new UserDaoJdbc();
		userDao.setDataSource(dataSource());
		return userDao;
	}
	
	@Bean
	public UserDaoSql userDaosql() {
		return new UserDaoSql();
	}
}