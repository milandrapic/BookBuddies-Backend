package com.drapic.bookbuddies.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.drapic.bookbuddies.dao.UserDAO;
import com.drapic.bookbuddies.entities.User;
import com.fasterxml.jackson.databind.ObjectMapper;

import net.minidev.json.JSONObject;

@RestController
@CrossOrigin
public class Controller {
	
	@Autowired
	private UserDAO dao;
	
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
	public User getUser(@RequestBody JSONObject s) {
ObjectMapper om = new ObjectMapper();
		User u = null;
		try {
			User user = om.readValue(s.toJSONString(), User.class);
			System.out.println(user.toString());
			u = this.dao.getUser(user.getUsername(), user.getPassword());
			System.out.println(u);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return u;
	}

}
