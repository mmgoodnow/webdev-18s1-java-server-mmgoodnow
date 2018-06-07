package webdev.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import webdev.models.Lesson;
import webdev.models.Module;
import webdev.repositories.CourseRepository;
import webdev.repositories.LessonRepository;
import webdev.repositories.ModuleRepository;

/**
 * Created by Michael Goodnow on 5/21/18.
 */

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class LessonService {
	@Autowired
	LessonRepository repo;

	@Autowired
	ModuleRepository moduleRepo;

	@Autowired
	CourseRepository courseRepo;

	@PostMapping("/api/course/{cid}/module/{mid}/lesson")
	public Lesson createLesson(@PathVariable("cid") int cid,
	                           @PathVariable("mid") int mid,
	                           @RequestBody Lesson lesson) {
		Optional<Module> opt = moduleRepo.findById(mid);
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
		return moduleRepo.findById(mid).map(Module::getLessons).orElse(null);
	}

	@GetMapping("/api/lesson/{id}")
	public Lesson findLessonById(@PathVariable("id") int id) {
		Optional<Lesson> opt = repo.findById(id);
		return opt.orElse(null);
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
