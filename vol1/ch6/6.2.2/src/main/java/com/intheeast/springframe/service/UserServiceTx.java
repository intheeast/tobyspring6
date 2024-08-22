package com.intheeast.springframe.service;

import java.sql.Connection;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import com.intheeast.springframe.dao.UserDao;
import com.intheeast.springframe.domain.Level;
import com.intheeast.springframe.domain.User;

public class UserServiceTx implements UserService{
	
	UserService userService;
	private PlatformTransactionManager transactionManager;

	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	public UserService getUserSerive() {
		return this.userService;
	}
	
	public void setTransactionManager(PlatformTransactionManager transactionManager) {
		this.transactionManager = transactionManager;
	}
	
	public PlatformTransactionManager getTransactionManager() {
		return this.transactionManager;
	}
	
	public void upgradeLevels() {
		TransactionStatus status = 
				this.transactionManager.getTransaction(new DefaultTransactionDefinition());
		
		try {
			
			this.userService.upgradeLevels();
			
			this.transactionManager.commit(status);
		} catch (RuntimeException e) {
			this.transactionManager.rollback(status);
			throw e;
		}
	}	
	
	public void add(User user) {
		this.userService.add(user);
	}
	
}
