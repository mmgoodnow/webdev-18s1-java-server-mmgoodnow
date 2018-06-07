package webdev.services.questions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;
import java.util.Optional;

import webdev.models.questions.MultipleChoiceExamQuestion;
import webdev.models.widgets.Exam;
import webdev.repositories.questions.MultipleChoiceQuestionRepository;
import webdev.repositories.widgets.ExamRepository;

/**
 * Created by Michael Goodnow on 6/6/18.
 */

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class MultipleChoiceQuestionService {
	@Autowired
	ExamRepository examRepo;

	@Autowired
	MultipleChoiceQuestionRepository repo;

	@PostMapping("/api/exam/{eid}/choice")
	public MultipleChoiceExamQuestion createChoiceQuestion(@PathVariable("eid") int eid,
	                                                       @RequestBody MultipleChoiceExamQuestion
		                                                       question) {
		Optional<Exam> opt = examRepo.findById(eid);
		if (opt.isPresent()) {
			Exam exam = opt.get();
			question.setExam(exam);
			return repo.save(question);
		}
		return null;
	}

	@PutMapping("/api/choice/{id}")
	public MultipleChoiceExamQuestion updateChoiceQuestion(@PathVariable("id") int id,
	                                                       @RequestBody MultipleChoiceExamQuestion
		                                                       newQuestion) {
		Optional<MultipleChoiceExamQuestion> opt = repo.findById(id);
		if (opt.isPresent()) {
			MultipleChoiceExamQuestion oldQuestion = opt.get();
			newQuestion.setId(id);
			return repo.save(newQuestion);
		}
		throw new NoSuchElementException();
	}

	@GetMapping("/api/choice/{id}")
	public MultipleChoiceExamQuestion getChoiceQuestion(@PathVariable("id") int id) {
		return repo.findById(id).orElse(null);
	}

	@DeleteMapping("/api/choice/{id}")
	public void deleteChoiceQuestion(@PathVariable("id") int id) {
		repo.deleteById(id);
	}

}
