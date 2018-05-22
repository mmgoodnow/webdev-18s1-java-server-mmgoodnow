package com.example.coursemanagement.services;

import com.example.coursemanagement.models.Course;
import com.example.coursemanagement.models.Module;
import com.example.coursemanagement.repositories.CourseRepository;
import com.example.coursemanagement.repositories.ModuleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * Created by Michael Goodnow on 5/21/18.
 */

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class ModuleService {
	@Autowired
	ModuleRepository repo;
	@Autowired
	CourseRepository courseRepo;

	@PostMapping("/api/course/{courseId}/module")
	public Module createModule(
		@PathVariable("courseId") int courseId,
		@RequestBody Module newModule) {
		Optional<Course> opt = courseRepo.findById(courseId);
		if(opt.isPresent()) {
			Course course = opt.get();
			newModule.setCourse(course);
			return repo.save(newModule);
		}
		return null;
	}

	@DeleteMapping("/api/module/{id}")
	public void deleteModule(@PathVariable("id") int id) {
		repo.deleteById(id);
	}

	@GetMapping("/api/module")
	public List<Module> findAllModules() {
		return (List<Module>) repo.findAll();
	}

	@GetMapping("/api/module/{id}")
	public Module findModuleById(@PathVariable("id") int id) {
		return repo.findById(id).orElse(null);
	}

	@GetMapping("/api/course/{cid}/module")
	public List<Module> findAllModulesForCourse(@PathVariable("cid") int cid) {
		return courseRepo.findById(cid).map(Course::getModules).orElse(null);
	}

	@PutMapping("/api/module/{id}")
	public Module updateModule(@PathVariable("id") int id,
	                         @RequestBody Module newModule) {
		Optional<Module> opt = repo.findById(id);
		if (opt.isPresent()) {
			Module module = opt.get();
			module.setTitle(newModule.getTitle());
			return repo.save(module);
		}
		throw new NoSuchElementException();
	}




}
