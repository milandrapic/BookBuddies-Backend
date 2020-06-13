package com.drapic.bookbuddies.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.drapic.bookbuddies.entities.Book;
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
		User u = this.entityManager.createQuery("select u from User u where username = :username", User.class).setParameter("username", username).getSingleResult();
		
		if(u.getPassword().equals(password)) {
			return u;
		}
		return null;
	}
	
	
	@Transactional
	public User getUserByUsername(String username) {
		User u = null;
		try {
		u = this.entityManager.createQuery("from User where username = :id", User.class).setParameter("id", username).getSingleResult();
		}
		catch (Exception e) {
			return null;
		}
		
		return u;
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
	
	@Transactional
	public void addBookFav(Book b, User u) {
		try {
		Book book = this.entityManager.createQuery("from Book where title = :title and author = :author", Book.class)
				.setParameter("title", b.getTitle())
				.setParameter("author", b.getAuthor())
				.getSingleResult();
		System.out.println(book.getTitle());
		System.out.println("book in DB");
			if(!u.getBookFavourites().contains(book)) {
				System.out.println("book is not favourites and is in books, and is added to favourites");
				System.out.println(u.getBookFavourites());
				u.addBookFavourite(b);
			}
			else {
				System.out.println("book is already in favourites, and is already in books");
			}
		}
		catch(Exception e) {
		entityManager.persist(b);
		System.out.println("book is not in DB, was just saved");
		
		try {
			List<Book> books = this.entityManager.createQuery("from Book", Book.class).getResultList();
			System.out.println(books);
			if(!u.getBookFavourites().contains(b)) {
				System.out.println("book is not favourites");
				u.addBookFavourite(b);
			}
			else {
				System.out.println("book is already in favourites, and is NOT already in books");
			}
			}
			catch (Exception ex) {
				
			}
		}
		
		
		
		
		
		
	}

	@Transactional
	public List<User> getUsers(String like){
		String query = "From User where username like '" + like + "%'";
		System.out.println(query);
		List<User> list = entityManager.createQuery(query, User.class).getResultList();
		return list;
	}

}
