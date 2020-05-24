package com.drapic.bookbuddies.dao;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.drapic.bookbuddies.entities.User;

@Repository
public class UserDAO {
	
	@Autowired
	private EntityManager entityManager;
	
	@Transactional
	public void createUser(User user) {
		String p = user.getPassword();
		String np = encrypt(p);
		user.setPassword(np);
		this.entityManager.persist(user);
	}
	
	@Transactional
	public User getUser(String username, String password) {
		password = this.encrypt(password);
		User u = this.entityManager.createQuery("from User where username = :id", User.class).setParameter("id", username).getSingleResult();
		
		if(u.getPassword().equals(password)) {
			return u;
		}
		return null;
	}
	


	private String encrypt(String pass) {
		String np = "";
		for(int i = 0; i< pass.length(); i++) {
			int ascii = (int) pass.charAt(i) + 3;
			char c = (char) ascii;
			np += c;
		}
		return np;
	}
	
//	private String decrypt(String pass) {
//		String np = "";
//		for(int i = 0; i< pass.length(); i++) {
//			int ascii = (int) pass.charAt(i) - 3;
//			char c = (char) ascii;
//			np += c;
//		}
//		return np;
//	}
}
