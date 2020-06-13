package com.drapic.bookbuddies.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="bookclub")
public class BookClub {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="club_id")
	private int clubId;
	
	@Column(name="title")
	private String title;
	
	@Column(name="group_book")
	private int groupBook;

	@ManyToOne( cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
	@JoinColumn(name="username")
	private User user;
	
	
	@Column(name="created_on")
	private String createdOn;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
	@JoinTable( 
				name="club_members",  
				joinColumns = @JoinColumn(name="club_id"),
				inverseJoinColumns = @JoinColumn(name="username")
			)
	private Set<User> members;
	
	public void addMember(User user) {
		if(this.members == null) {
			this.members = new HashSet<User>();
		}
		
		if(!this.members.contains(user)) {
			this.members.add(user);
		}
	}
	


	public Set<User> getMembers() {
		return members;
	}


	public void setMembers(Set<User> members) {
		this.members = members;
	}


	public int getClubId() {
		return clubId;
	}


	public void setClubId(int clubId) {
		this.clubId = clubId;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public int getGroupBook() {
		return groupBook;
	}


	public void setGroupBook(int groupBook) {
		this.groupBook = groupBook;
	}

	

	public User getUser() {
		return user;
	}



	public void setUser(User user) {
		this.user = user;
	}



	public String getCreatedOn() {
		return createdOn;
	}


	public void setCreatedOn(String createdOn) {
		this.createdOn = createdOn;
	}



	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("BookClub [clubId=");
		builder.append(clubId);
		builder.append(", title=");
		builder.append(title);
		builder.append(", groupBook=");
		builder.append(groupBook);
		builder.append(", user=");
		builder.append(user);
		builder.append(", createdOn=");
		builder.append(createdOn);
		builder.append(", members=");
		builder.append(members);
		builder.append("]");
		return builder.toString();
	}


	
	

}
