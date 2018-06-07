package webdev.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import webdev.models.User;
import webdev.repositories.UserRepository;

/**
 * Created by Michael Goodnow on 5/10/18.
 */

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class UserService {

	@Autowired
	UserRepository repo;

	@PostMapping("/api/login")
	public User login(@RequestBody User creds, HttpSession session) {
		Iterator<User> iterator = repo.findUserByCredentials(
			creds.getUsername(), creds.getPassword()).iterator();

		if (iterator.hasNext()) {
			session.setAttribute("currentUser", creds);
			return iterator.next();
		} else throw new IllegalArgumentException();

	}

	@PostMapping("/api/logout")
	public void logout(HttpSession session) {
		session.invalidate();
	}

	@GetMapping("/api/profile")
	public User profile(HttpSession session) {
		User user = (User) session.getAttribute("currentUser");
		return repo.findUserByUsername(user.getUsername()).iterator().next();
	}

	@GetMapping("/api/user")
	public List<User> findUserByUsername(@RequestParam(value = "username", required = false)
		                                     String username) {
		if (username == null) return findAllUsers();
		for (User u : repo.findAll()) {
			if (u.getUsername().equals(username)) {
				return Collections.singletonList(u);
			}
		}
		return null;
	}

	private List<User> findAllUsers() {
		return (List<User>) repo.findAll();
	}

	@PostMapping("/api/register")
	public User register(@RequestBody User user, HttpSession session) {
		if (repo.findUserByUsername(user.getUsername()).iterator().hasNext()) {
			throw new IllegalArgumentException("Username taken");
		}
		session.setAttribute("currentUser", user);
		return repo.save(user);
	}

	@PostMapping("/api/user")
	public User createUser(@RequestBody User user) {
		return repo.save(user);
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
