package com.drapic.bookbuddies.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="club_requests")
public class ClubRequest {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="request_id")
	private int request_id;
	
	@Column(name="from_username")
	private String from_username;
	
	@Column(name="to_username")
	private String to_username;
	
	@Column(name="club_id")
	private int club_id;

	public int getRequest_id() {
		return request_id;
	}

	public void setRequest_id(int request_id) {
		this.request_id = request_id;
	}

	

	public String getFrom_username() {
		return from_username;
	}

	public void setFrom_username(String from_username) {
		this.from_username = from_username;
	}

	public String getTo_username() {
		return to_username;
	}

	public void setTo_username(String to_username) {
		this.to_username = to_username;
	}

	public int getClub_id() {
		return club_id;
	}

	public void setClub_id(int club_id) {
		this.club_id = club_id;
	}
	
	
}
