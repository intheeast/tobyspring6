package com.intheeast.springframe.servicetest;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.mail.MailSender;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.intheeast.springframe.dao.UserDao;
import com.intheeast.springframe.dao.UserDaoJdbc;
import com.intheeast.springframe.service.DummyMailSender;
import com.intheeast.springframe.service.UserService;
import com.intheeast.springframe.service.UserServiceImpl;
import com.intheeast.springframe.sqlservice.SqlRegistry;
import com.intheeast.springframe.sqlservice.SqlService;
import com.intheeast.springframe.sqlservice.updatable.EmbeddedDbSqlRegistry;
import com.intheeast.springframe.servicetest.UserServiceTest.TestUserService;
import com.mysql.cj.jdbc.Driver;

@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = "com.intheeast.springframe")
public class TestApplicationContext {
	/**
	 * DB연결과 트랜잭션
	 */
	
	@Bean
	@Qualifier("jdbcDataSource")
	public DataSource dataSource() {
		SimpleDriverDataSource ds = new SimpleDriverDataSource();
		ds.setDriverClass(Driver.class);
		ds.setUrl("jdbc:mysql://localhost:3306/sbdt_db?characterEncoding=UTF-8");
		ds.setUsername("root");
		ds.setPassword("1234");
		return ds;
	}
	
	@Bean
	public PlatformTransactionManager transactionManager() {
		DataSourceTransactionManager tm = new DataSourceTransactionManager();
		tm.setDataSource(dataSource());
		return tm;
	}
	
	/**
	 * 애플리케이션 로직 & 테스트용 빈
	 */
	
	@Autowired 
	UserDao userDao;		
	
	
	@Bean
	public UserService testUserService() {
		TestUserService testService = new TestUserService();
		testService.setUserDao(this.userDao);
		testService.setMailSender(mailSender());
		return testService;
	}
	
	@Bean
	public MailSender mailSender() {
		return new DummyMailSender();
	}
	
	
	
	@Bean
	public SqlRegistry sqlRegistry() {
		EmbeddedDbSqlRegistry sqlRegistry = new EmbeddedDbSqlRegistry();
		sqlRegistry.setDataSource(embeddedDatabase());
		return sqlRegistry;
	}
	
	
	
	@Bean	
	public DataSource embeddedDatabase() {
		return new EmbeddedDatabaseBuilder()
			.setName("embeddedDatabase")
			.setType(EmbeddedDatabaseType.H2)
			.addScript("sqlRegistrySchema.sql")
			.build();
	}
}