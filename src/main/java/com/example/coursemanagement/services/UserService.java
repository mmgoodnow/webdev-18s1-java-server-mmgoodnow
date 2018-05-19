package com.example.coursemanagement.services;

import com.example.coursemanagement.models.User;
import com.example.coursemanagement.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * Created by Michael Goodnow on 5/10/18.
 */

@RestController
public class UserService {

	@Autowired
	UserRepository repo;

	@PostMapping("/api/login")
	public List<User> login(@RequestBody User user) {
		return (List<User>) repo.findUserByCredentials(user.getUsername(), user.getPassword());
	}

	@GetMapping("/api/user")
	public User findUserByUsername(@RequestParam(value="username", required=true) String username) {
		for (User u : repo.findAll()) {
			if (u.getUsername().equals(username)) {
				return u;
			}
		}
		return null;
	}

	@PostMapping("/api/register")
	public User register(@RequestBody User user) {
		if (repo.findUserByUsername(user.getUsername()).iterator().hasNext())
			throw new IllegalArgumentException("Username taken");
		return repo.save(user);
	}

	@PostMapping("/api/user")
	public User createUser(@RequestBody User user) {
		return repo.save(user);
	}

	@GetMapping("/api/user")
	public List<User> findAllUsers() {
		return (List<User>) repo.findAll();
	}

	@GetMapping("/api/user/{userId}")
	public User findUserById(@PathVariable("userId") int id) {
		return repo.findById(id).orElse(null);
	}

	@PutMapping("/api/user/{userId}")
	public User updateUser(@PathVariable("userId") int uid, @RequestBody User newUser) {
		Optional<User> optuser = repo.findById(uid);
		if (!optuser.isPresent()) throw new NoSuchElementException();
		User user = optuser.get();
		user.setFirstName(newUser.getFirstName());
		user.setLastName(newUser.getLastName());
		user.setPassword(newUser.getPassword());
		user.setUsername(newUser.getUsername());
		user.setEmail(newUser.getEmail());
		user.setRole(newUser.getRole());
		user.setPhone(newUser.getPhone());
		user.setDob(newUser.getDob());
		repo.save(user);
		return user;
	}

	@DeleteMapping("/api/user/{userId}")
	public void deleteUser(@PathVariable("userId") int id) {
		repo.deleteById(id);
	}
}
