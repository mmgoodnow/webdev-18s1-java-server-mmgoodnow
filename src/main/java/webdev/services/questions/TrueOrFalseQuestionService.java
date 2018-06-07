package webdev.services.questions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;
import java.util.Optional;

import webdev.models.questions.TrueOrFalseExamQuestion;
import webdev.models.widgets.Exam;
import webdev.repositories.questions.TrueOrFalseQuestionRepository;
import webdev.repositories.widgets.ExamRepository;

/**
 * Created by Michael Goodnow on 6/6/18.
 */

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class TrueOrFalseQuestionService {
	@Autowired
	ExamRepository examRepo;

	@Autowired
	TrueOrFalseQuestionRepository repo;

	@PostMapping("/api/exam/{eid}/truefalse")
	public TrueOrFalseExamQuestion createTrueFalseQuestion(@PathVariable("eid") int eid,
	                                                       @RequestBody TrueOrFalseExamQuestion
		                                                       question) {
		Optional<Exam> opt = examRepo.findById(eid);
		if (opt.isPresent()) {
			Exam exam = opt.get();
			question.setExam(exam);
			return repo.save(question);
		}
		return null;
	}

	@PutMapping("/api/truefalse/{id}")
	public TrueOrFalseExamQuestion updateTrueFalseQuestion(@PathVariable("id") int id,
	                                                       @RequestBody TrueOrFalseExamQuestion
		                                                       newQuestion) {
		Optional<TrueOrFalseExamQuestion> opt = repo.findById(id);
		if (opt.isPresent()) {
			TrueOrFalseExamQuestion oldQuestion = opt.get();
			newQuestion.setId(id);
			return repo.save(newQuestion);
		}
		throw new NoSuchElementException();
	}

	@GetMapping("/api/truefalse/{id}")
	public TrueOrFalseExamQuestion getTrueFalseQuestion(@PathVariable("id") int id) {
		return repo.findById(id).orElse(null);
	}

	@DeleteMapping("/api/truefalse/{id}")
	public void deleteTrueFalseQuestion(@PathVariable("id") int id) {
		repo.deleteById(id);
	}
}
