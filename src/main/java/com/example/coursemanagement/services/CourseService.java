package com.example.coursemanagement.services;

import com.example.coursemanagement.models.Course;
import com.example.coursemanagement.repositories.CourseRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * Created by Michael Goodnow on 5/21/18.
 */

@RestController
public class CourseService {

	@Autowired
	CourseRepository repo;

	@PostMapping("/api/course")
	Course createCourse(@RequestBody Course course) {
		course.setCreated(new Date());
		course.setModified(new Date());
		return repo.save(course);
	}

	@DeleteMapping("/api/course/{id}")
	void deleteCourse(@PathVariable("id") int id) {
		repo.deleteById(id);
	}

	@GetMapping("/api/course")
	List<Course> findAllCourses() {
		return (List<Course>) repo.findAll();
	}

	@GetMapping("/api/course/{id}")
	Course findCourseById(@PathVariable("id") int id) {
		return repo.findById(id).orElse(null);
	}

	@PutMapping("/api/course")
	Course updateCourse(@RequestBody Course newCourse) {
		Optional<Course> opt = repo.findById(newCourse.getId());
		if (opt.isPresent()) {
			Course course = opt.get();
			course.setModified(new Date());
			course.setTitle(newCourse.getTitle());
			course.setModules(newCourse.getModules());
			return repo.save(course);
		}
		throw new NoSuchElementException();
	}
}
