package com.drapic.bookbuddies.dao;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.drapic.bookbuddies.entities.BookClub;
import com.drapic.bookbuddies.entities.ClubRequest;
import com.drapic.bookbuddies.entities.User;


@Repository
public class ClubDAO {
	
	@Autowired
	private UserDAO userDAO;

	@Autowired
	private EntityManager entityManager;
	
	@Transactional
	public void createClub(BookClub club, List<User> members) {
		//entityManager.persist(club);
		System.out.println("IN CREATE CLUB");
		System.out.println("User:" + club.getUser().toString());
		System.out.println("Club:" + club);
		Set<User> mems = new HashSet<User>();
		for(User u : members) {
			User newU = this.userDAO.getUserByUsername(u.getUsername());
			if(newU != null)
				newU.addMyClub(club);
			
			mems.add(newU);
		}
		club.setMembers(mems);
		entityManager.merge(club);
		//User admin = club.getUser();
		
		
	}
	
	@Transactional
	public void sendRequest(User admin, List<User> members, BookClub club) {
		for(User u:members) {
			ClubRequest cr = new ClubRequest();
			cr.setFrom_username(admin.getUsername());
			cr.setTo_username(u.getUsername());
			cr.setClub_id(club.getClubId());
			this.entityManager.persist(cr);
		}
	}
	
	
	
}
