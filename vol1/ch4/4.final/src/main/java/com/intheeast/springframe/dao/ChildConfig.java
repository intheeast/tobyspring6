package com.intheeast.springframe.dao;

import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ChildConfig {
	
	@Bean
    public AccountService accountService() {
        ProxyFactoryBean proxyFactoryBean = new ProxyFactoryBean();
        proxyFactoryBean.setTarget(accountService()); // 부모 컨텍스트의 빈 참조
        return (AccountService) proxyFactoryBean.getObject();
    }

}
