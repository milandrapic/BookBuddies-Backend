package com.drapic.bookbuddies.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.drapic.bookbuddies.dao.ClubDAO;
import com.drapic.bookbuddies.dao.UserDAO;
import com.drapic.bookbuddies.entities.Book;
import com.drapic.bookbuddies.entities.BookClub;
import com.drapic.bookbuddies.entities.BookFav;
import com.drapic.bookbuddies.entities.User;
import com.fasterxml.jackson.databind.ObjectMapper;

import net.minidev.json.JSONObject;

@RestController
@CrossOrigin
public class Controller {
	
	@Autowired
	private UserDAO dao;
	
	@Autowired
	private ClubDAO clubDao;
	
	@PostMapping("/create")
 	public void createUser(@RequestBody JSONObject s) {

		ObjectMapper om = new ObjectMapper();
		
		try {
			User user = om.readValue(s.toJSONString(), User.class);
			System.out.println(user.toString());
			this.dao.createUser(user);
		}
		catch(DataIntegrityViolationException e) {
			System.out.println("user exists");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@PostMapping("/getUser")
	public User getUser(@RequestBody User user) {
			
//		ObjectMapper om = new ObjectMapper();
//		System.err.println(s);
		User u = null;
		try {
//			User user = om.readValue(s, User.class);
			
			u = this.dao.getUser(user.getUsername(), user.getPassword());
			System.out.println(user.toString());
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return u;
	}
	
	@GetMapping("/getFavs/{username}")
	public List<Book> getBookFavs(@PathVariable String username){
		
		User u = this.dao.getUserByUsername(username);
		
		
		return u.getBookFavourites();
		
	}
	 
	@PostMapping("/setFav")
	public void setFav(@RequestBody BookFav bookFav) {
		System.out.println("in setFav");
		String uName = bookFav.getUser().getUsername();
		User u = this.dao.getUserByUsername(uName);
		dao.addBookFav(bookFav.getBook(), u);
	}
	
	@PostMapping("/getUsers")
	public List<String> getUsers(@RequestBody String[] username) {
		List<User> userList = this.dao.getUsers(username[1]);
		
		System.out.println(userList);
		
		List<String> users = new ArrayList<String>();
		
		for(User u: userList) {
			if(!u.getUsername().equals(username[0])) {
				users.add(u.getUsername());
			}
		}
		
		
		
		return users;
	}
	
	@PostMapping("/createClub")
	public void createClub(@RequestBody BookClub club) {
		System.out.println(club.getUser().getUsername());
		List<User> members = new ArrayList<>();
		User usr = this.dao.getUserByUsername(club.getUser().getUsername());
		
		club.setUser(usr);
		System.out.println(club.getUser());
		for(User u: club.getMembers()) {
			User user = this.dao.getUserByUsername(u.getUsername());
			members.add(user);
		}
		
		//System.out.println(members);
		//System.out.println(club);
		
		this.clubDao.createClub(club, members);
		
	}

}








