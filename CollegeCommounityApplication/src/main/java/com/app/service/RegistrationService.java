package com.app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.model.User;
import com.app.repository.RegistrationRepository;

@Service
public class RegistrationService {

	@Autowired
	private RegistrationRepository repo;

	public User saveUser(User user) {
		return repo.save(user);
		// Save method returning the user /and Store object in database
	}

	public User fetchUserByEmailId(String email) {
		return repo.findByEmailId(email);
	}
	
	

	public User fetchUserByEmailIdAndPassword(String email, String password) {
		return repo.findByEmailIdAndPassword(email, password);
	}

	
	
	public List<User> getUserProfile() {
		return repo.findAll();
	}

	
	
	public ArrayList<Object> getFullName() {
		List<User> existUser = repo.findAll();
		ArrayList<Object> userFullName = new ArrayList<>();

		for (User txn : existUser) {
			userFullName.add(txn.getFullName());

		}
		return userFullName;
	}

}
