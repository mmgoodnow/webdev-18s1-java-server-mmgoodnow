package webdev.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import webdev.models.Course;
import webdev.models.Module;
import webdev.repositories.CourseRepository;
import webdev.repositories.ModuleRepository;

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
		if (opt.isPresent()) {
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
