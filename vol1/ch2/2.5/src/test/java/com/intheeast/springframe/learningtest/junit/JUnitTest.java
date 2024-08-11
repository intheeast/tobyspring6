package com.intheeast.springframe.learningtest.junit;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.intheeast.springframe.learningtest.factorybean.AppConfig;
import com.intheeast.springframe.learningtest.factorybean.MyCustomObject;
import com.intheeast.springframe.learningtest.factorybean.MyCustomObjectFactoryBean;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {TestJunit.class})
//@DirtiesContext
public class JUnitTest {
	@Autowired 
	ApplicationContext context; // Spring IoC Container가 자동 주입됨.
	
	// Set은 동일한 엘리먼트 중복을 허용하지 않는다.
	// 엘리먼트 : 특정 JUnitTest 클래스 오브젝트(인스턴스) 참조(C언어에서 포인터:값으로 메모리 주소) 값
	static Set<JUnitTest> testObjects = new HashSet<>();
	static ApplicationContext contextObject = null;
	
	@Test 
	public void test1() {
		assertFalse(testObjects.contains(this));
		testObjects.add(this);
		
		assertTrue(contextObject == null || contextObject == this.context);
		contextObject = this.context;		
	}
	
	@Test 
	public void test2() {
		assertFalse(testObjects.contains(this));
		testObjects.add(this);
		
		assertTrue(contextObject == null || contextObject == this.context);
		contextObject = this.context;
	}
	
	@DirtiesContext
	@Test 
	public void test3() throws IllegalStateException, Exception {
		
		assertFalse(testObjects.contains(this));
		testObjects.add(this);
		
		// Bean Scope : singleton, prototype, request[Http Request]
		ConfigurableApplicationContext configAppContext = 
				(ConfigurableApplicationContext) context;
		
		// 빈으로 등록된 FactoryBean은 getBean으로 가져올 때 '&'를 붙여야 함.
		// 그렇지 않으면, FactoryBean이 생성하는 빈 객체를 리턴함!!!
		MyCustomObjectFactoryBean factoryBean =
				(MyCustomObjectFactoryBean) context.getBean("&myCustomObjectFactory");
		
		configAppContext.getBeanFactory().registerSingleton("myCustomObject2", 
				factoryBean.getObject());
		
		MyCustomObject myObject2 = 
				(MyCustomObject) context.getBean("myCustomObject2");
		
		Assertions.assertNotNull(myObject2);		
		
		assertTrue(contextObject == null || contextObject == this.context);
		contextObject = this.context;
	}
	
	@Test 
	public void test4() {
		assertFalse(testObjects.contains(this));
		testObjects.add(this);
		
		assertTrue(contextObject == null || contextObject == this.context);
		contextObject = this.context;
	}
}