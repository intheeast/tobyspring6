package com.intheeast.springframe.daotest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.AssertThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.ClassOrderer.OrderAnnotation;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.intheeast.springframe.dao.DaoFactory;
import com.intheeast.springframe.dao.UserDao;
import com.intheeast.springframe.domain.User;

//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
//@TestMethodOrder(MethodOrderer.DisplayName.class)
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {DaoFactory.class})
@DirtiesContext
public class UserDaoTest {
	@Autowired
	ApplicationContext context;
	
	private UserDao userDao; 
	
	private User user1;
	private User user2;
	private User user3;
	
	@BeforeAll
	public static void init() {
		System.out.println("init");
	}
	
	@BeforeEach
	public void setUp() {

		this.userDao = context.getBean("userDao", UserDao.class);
		
		
		this.user1 = new User("gyumee", "¹Ú¼ºÃ¶", "springno1");
		this.user2 = new User("leegw700", "ÀÌ±æ¿ø", "springno2");
		this.user3 = new User("bumjin", "¹Ú¹üÁø", "springno3");
		
		System.setProperty("configFile", "test-config.properties");

	}
	
	@Test
//	@Order(1)
	public void andAndGet() throws SQLException {
				
		// boolean condition = true;
		// ...
		// condition = false;(true)
		// ...
		// assert condition -> 프로그램 중단되고 AssertError 발생
		// 
		// Assert that expected and actual are equal.
		userDao.deleteAll();
		assertEquals(userDao.getCount(), 0);
		
		User user1 = new User("gyumee", "박성철", "springno1");
		User user2 = new User("leegw700", "이길원", "springno2");
		

		userDao.deleteAll();
		assertEquals(userDao.getCount(), 0);
	

		userDao.add(user1);
		userDao.add(user2);
		assertEquals(userDao.getCount(), 2);
		
		User userget1 = userDao.get(user1.getId());
		assertEquals(userget1.getName(), user1.getName());
		assertEquals(userget1.getPassword(), user1.getPassword());
		
		User userget2 = userDao.get(user2.getId());
		assertEquals(userget2.getName(), user2.getName());
		assertEquals(userget2.getPassword(), user2.getPassword());
	}
	
	@Test
//	@Order(2)
	public void getUserFailure() throws SQLException, ClassNotFoundException {		
		userDao.deleteAll();
		assertEquals(userDao.getCount(), 0);	
		
//		public interface Executable {
//
//			void execute() throws Throwable;
//
//		}
		// Assert that 
		// execution of the supplied executable throws an exception of the expectedType 
		// and return the exception. 
		// If no exception is thrown, or if an exception of a different type is thrown, 
		// this method will fail.
		// If you do not want to perform additional checks on the exception instance,
		// ignore the return value.

//		public static <T extends Throwable> T assertThrows(
//				Class<T> expectedType, 
//				Executable executable) {
//			return AssertThrows.assertThrows(expectedType, executable);
//		}
				
		// dao.get("unknown_id")가 EmptyResultDataAccessException.class 예외를 발생시키면,
		// 이 단위 테스트 성공...
		// 만약 그렇지 않으면-어떠한 예외도 발생하지 않거나, EmptyResultDataAccessException이 아닌 
		// 다른 예외가 발생하면, 이 단위 테스트는 실패!!!
		Assertions.assertThrows(EmptyResultDataAccessException.class, 
				// Excutable 인터페이스의 excute 메소드의 람다!!!
				() -> userDao.get("unknown_id"));	
		
		
//		Runnable runnable = checkRunnable();
//		runnable.run();
	}
	
//	public Runnable checkRunnable() {
//		int a = 3;
//		// void run();
//		Runnable run = () -> {
//			int b = a + 3;
//			
//		};
//		
//		return run;
//	}
	
	@Test
	@DisplayName("count")
//	@Order(3)
	public void count() throws SQLException {
		
				
		userDao.deleteAll();
		assertEquals(userDao.getCount(), 0);
				
		userDao.add(user1);
		assertEquals(userDao.getCount(), 1);
		
		userDao.add(user2);
		assertEquals(userDao.getCount(), 2);
		
		userDao.add(user3);
		assertEquals(userDao.getCount(), 3);
	}
	
	@AfterEach
	public void afterEach() {
		System.clearProperty("configFile");
	}
	
	@AfterAll
	public static void goodBye() {
		System.out.println();
	}

}
