package webdev.services.widgets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import webdev.models.Lesson;
import webdev.models.widgets.Assignment;
import webdev.repositories.LessonRepository;
import webdev.repositories.widgets.AssignmentRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * Created by Michael Goodnow on 6/6/18.
 */

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class AssignmentService {

	@Autowired
	AssignmentRepository repo;

	@Autowired
	LessonRepository lessonRepo;


	@GetMapping("/api/assignment")
	public List<Assignment> findAllAssignments() {
		return (List<Assignment>) repo.findAll();
	}

	@GetMapping("/api/assignment/{assignmentId}")
	public Assignment findAssignmentById(@PathVariable("assignmentId") int id) {
		return repo.findById(id).orElse(null);
	}

	@GetMapping("/api/lesson/{lid}/assignment")
	public List<Assignment> findAllAssignmentsForLesson(@PathVariable("lid") int lid) {
		return (List<Assignment>) repo.findAllByLessonId(lid);
	}

	@PostMapping("/api/lesson/{lid}/assignment")
	public Assignment createAssignment(@PathVariable("lid") int lid,
	                                   @RequestBody Assignment assignment) {
		Optional<Lesson> opt = lessonRepo.findById(lid);

		if (opt.isPresent()) {
			assignment.setLesson(opt.get());
			return repo.save(assignment);
		}

		throw new NoSuchElementException();
	}

	@PutMapping("/api/assignment/{aid}")
	public Assignment updateAssignment(@PathVariable("aid") int id,
									   @RequestBody Assignment assignment) {
		Optional<Assignment> opt = repo.findById(id);
		if (opt.isPresent()) {
			assignment.setId(id);
			assignment.setLesson(opt.get().getLesson());
			return repo.save(assignment);
		}
		throw new NoSuchElementException();
	}

	@DeleteMapping("/api/assignment/{assignmentId}")
	public void deleteAssignmentById(@PathVariable("assignmentId") int id) {
		repo.deleteById(id);
	}
}
