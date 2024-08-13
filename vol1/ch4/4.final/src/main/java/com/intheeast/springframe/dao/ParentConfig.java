package com.intheeast.springframe.dao;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ParentConfig {
	
	
	@Bean
    public AccountService accountService() {
        return new SimpleAccountService();
    }

}
