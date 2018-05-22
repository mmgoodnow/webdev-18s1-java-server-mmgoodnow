package com.example.coursemanagement.services;

import com.example.coursemanagement.models.Course;
import com.example.coursemanagement.repositories.CourseRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Michael Goodnow on 5/21/18.
 */

@RestController
public class CourseService {

	@Autowired
	CourseRepository repo;

	@PostMapping("/api/course")
	Course createCourse(@RequestBody Course course) {
		return repo.save(course);
	}

	@DeleteMapping("/api/course/{courseId}")
		void deleteCourse(@PathVariable("courseId") int id) {

	}
}
