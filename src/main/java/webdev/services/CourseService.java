package webdev.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import webdev.models.Course;
import webdev.repositories.CourseRepository;

/**
 * Created by Michael Goodnow on 5/21/18.
 */

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class CourseService {

	@Autowired
	CourseRepository repo;

	@PostMapping("/api/course")
	public Course createCourse(@RequestBody Course course) {
		course.setCreated(new Date());
		course.setModified(new Date());
		return repo.save(course);
	}

	@DeleteMapping("/api/course/{cid}")
	public void deleteCourse(@PathVariable("cid") int id) {
		repo.deleteById(id);
	}

	@GetMapping("/api/course")
	public List<Course> findAllCourses() {
		return (List<Course>) repo.findAll();
	}

	@GetMapping("/api/course/{cid}")
	public Course findCourseById(@PathVariable("cid") int id) {
		return repo.findById(id).orElse(null);
	}

	@PutMapping("/api/course")
	public Course updateCourse(@RequestBody Course newCourse) {
		Optional<Course> opt = repo.findById(newCourse.getId());
		if (opt.isPresent()) {
			Course course = opt.get();
			course.setModified(new Date());
			course.setTitle(newCourse.getTitle());
			course.setOwner(newCourse.getOwner());
			return repo.save(course);
		}
		throw new NoSuchElementException();
	}
}
