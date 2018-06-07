package webdev.services.widgets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import webdev.models.Lesson;
import webdev.models.widgets.Exam;
import webdev.repositories.LessonRepository;
import webdev.repositories.widgets.ExamRepository;

/**
 * Created by Michael Goodnow on 6/6/18.
 */

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class ExamService {

	@Autowired
	ExamRepository repo;

	@Autowired
	LessonRepository lessonRepo;


	@GetMapping("/api/exam")
	public List<Exam> findAllExams() {
		return (List<Exam>) repo.findAll();
	}

	@GetMapping("/api/exam/{examId}")
	public Exam findExamById(@PathVariable("examId") int id) {
		return repo.findById(id).orElse(null);
	}

	@GetMapping("/api/lesson/{lid}/exam")
	public List<Exam> findAllExamsForLesson(@PathVariable("lid") int lid) {
		return (List<Exam>) repo.findAllByLessonId(lid);
	}

	@PostMapping("/api/lesson/{lid}/exam")
	public Exam createExam(@PathVariable("lid") int lid, @RequestBody Exam exam) {
		Optional<Lesson> opt = lessonRepo.findById(lid);

		if (opt.isPresent()) {
			exam.setLesson(opt.get());
			repo.save(exam);
		}

		throw new NoSuchElementException();
	}

	@DeleteMapping("/api/exam/{examId}")
	public void deleteExamById(@PathVariable("examId") int id) {
		repo.deleteById(id);
	}

}