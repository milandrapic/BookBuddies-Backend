package com.drapic.bookbuddies.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="users")
public class User {

	@Id
	@Column(name="username")
	private String username;
	
	@Column(name="email")
	private String email;
	
	@Column(name="password")
	private String password;
	
//	@JsonIgnore
	@ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
	@JoinTable( 
				name="book_favourites",  
				joinColumns = @JoinColumn(name="username"),
				inverseJoinColumns = @JoinColumn(name="book_id")
			)
	private List<Book> bookFavourites;
	
//	@JsonIgnore
	@OneToMany( cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
	private List<BookClub> bookClubs;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
	@JoinTable( 
				name="my_clubs",  
				joinColumns = @JoinColumn(name="username"),
				inverseJoinColumns = @JoinColumn(name="club_id")
			)
	private List<BookClub> myClubs;
	
	
	public void addMyClub(BookClub club) {
		if(this.myClubs == null) {
			this.myClubs = new ArrayList<BookClub>();
		}
		this.myClubs.add(club);
	}
	

	public List<Book> getBookFavourites() {
		return bookFavourites;
	}

	public void setBookFavourites(List<Book> bookFavourites) {
		this.bookFavourites = bookFavourites;
	}

	
	public void addBookFavourite(Book b) {
		if(this.bookFavourites == null) {
			this.bookFavourites = new ArrayList<Book>();
		}
		
		this.bookFavourites.add(b);
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	 
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", email=" + email + ", password=" + password + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}
	
	
	
}
