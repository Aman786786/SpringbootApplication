package com.app.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.app.model.User;
import com.app.repository.RegistrationRepository;
import com.app.service.RegistrationService;

@RestController
//Response In Form of JASON
//By Default every method in RestController will return response not UI 

public class RegistrationController {

//this method will be call

	@Autowired
	private RegistrationService service;

	@Autowired
	private RegistrationRepository repo;

	@PostMapping("/registeruser")
	public User registerUser(@RequestBody User user) throws Exception {
		String tempEmailId = user.getEmailId();
		if (tempEmailId != null && !"".equals(tempEmailId)) {
			User userobj = service.fetchUserByEmailId(tempEmailId);
			if (userobj != null) {

				throw new Exception("user with " + tempEmailId + " is already exist");
			}
		}
		User userObj = null;
		userObj = service.saveUser(user);
		return userObj;
	}

	@PostMapping("/login")
	public User loginUser(@RequestBody User user) throws Exception {
		String tempEmailId = user.getEmailId();
		String tempPass = user.getPassword();
		User userObj = null;

		if (tempEmailId != null && tempPass != null) {
			userObj = service.fetchUserByEmailIdAndPassword(tempEmailId, tempPass);
		}

		if (userObj == null) {
			throw new Exception("Bad Credentials");
		}

		return userObj;
	}

	@GetMapping("/user/profile")
	// Get List of User Profile
	public List<User> userProfile() {
		return service.getUserProfile();
	}

	@GetMapping("/fullname")
	// Get List of Full name
	public ArrayList<Object> getFullName() {
		return service.getFullName();
	}
	

	@PutMapping("/update/profile/{id}")
	public User updateProfile(@PathVariable int id, @RequestBody User user) {

		User exUser = repo.getById(id);
		exUser.setEmailId(user.getEmailId());
		exUser.setPassword(user.getPassword());
		exUser.setFullName(user.getFullName());
		exUser.setContact(user.getContact());

//	   User  updatedProfile = ;
		return repo.save(exUser);

	}

}
