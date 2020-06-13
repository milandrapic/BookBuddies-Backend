package com.drapic.bookbuddies.entities;

public class BookFav {
	private Book book;
	private User user;
	
	public BookFav(Book book, User user) {
		this.book = book;
		this.user = user;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
	
	
	

}
