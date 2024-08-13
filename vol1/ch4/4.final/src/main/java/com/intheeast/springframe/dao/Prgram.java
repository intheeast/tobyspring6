package com.intheeast.springframe.dao;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Prgram {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext parentContext = new AnnotationConfigApplicationContext(ParentConfig.class);
        AnnotationConfigApplicationContext childContext = new AnnotationConfigApplicationContext(ChildConfig.class);
        childContext.setParent(parentContext);
        
        AccountService accountService = childContext.getBean("accountService", AccountService.class);


	}

}
