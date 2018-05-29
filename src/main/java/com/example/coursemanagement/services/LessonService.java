package com.example.coursemanagement.services;

import com.example.coursemanagement.models.Lesson;
import com.example.coursemanagement.models.Module;
import com.example.coursemanagement.repositories.CourseRepository;
import com.example.coursemanagement.repositories.LessonRepository;
import com.example.coursemanagement.repositories.ModuleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * Created by Michael Goodnow on 5/21/18.
 */

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class LessonService {
	@Autowired
	LessonRepository repo;

	@Autowired
	ModuleRepository moduleRepository;

	@Autowired
	CourseRepository courseRepository;

	@PostMapping("/api/course/{cid}/module/{mid}/lesson")
	public Lesson createLesson(@PathVariable("cid") int cid,
	                           @PathVariable("mid") int mid,
	                           @RequestBody Lesson lesson) {
		Optional<Module> opt = moduleRepository.findById(mid);
		if (opt.isPresent()) {
			Module mod = opt.get();
			lesson.setModule(mod);
			return repo.save(lesson);
		}
		return null;
	}

	@DeleteMapping("/api/lesson/{id}")
	public void deleteLesson(@PathVariable("id") int id) {
		repo.deleteById(id);
	}

	@GetMapping("/api/lesson")
	public List<Lesson> findAllLessons() {
		return (List<Lesson>) repo.findAll();
	}

	@GetMapping("/api/course/{cid}/module/{mid}/lesson")
	public List<Lesson> findAllLessonsForModule(@PathVariable("cid") int cid,
	                                            @PathVariable("mid") int mid) {
		return moduleRepository.findById(mid).map(Module::getLessons).orElse(null);
	}

	@PutMapping("/api/lesson/{id}")
	public Lesson updateLesson(@PathVariable("id") int id,
	                           @RequestBody Lesson newLesson) {
		Optional<Lesson> opt = repo.findById(id);
		if (opt.isPresent()) {
			Lesson lesson = opt.get();
			lesson.setTitle(newLesson.getTitle());
			return repo.save(lesson);
		}
		throw new NoSuchElementException();
	}
}
