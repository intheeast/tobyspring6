package com.intheeast.springframe.sandbox;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.intheeast.springframe.dao.DaoFactory;
import com.intheeast.springframe.dao.UserDao;

public class SingletonTest {
	
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);
		
		System.out.println(context.getBean(UserDao.class));
		System.out.println(context.getBean(UserDao.class));
	}

}
