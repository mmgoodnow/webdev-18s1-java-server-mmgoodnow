package webdev.services.questions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;
import java.util.Optional;

import webdev.models.questions.FillInTheBlanksExamQuestion;
import webdev.models.widgets.Exam;
import webdev.repositories.questions.FillInTheBlanksQuestionRepository;
import webdev.repositories.widgets.ExamRepository;

/**
 * Created by Michael Goodnow on 6/6/18.
 */

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class FillInTheBlanksQuestionService {
	@Autowired
	ExamRepository examRepo;

	@Autowired
	FillInTheBlanksQuestionRepository repo;

	@PostMapping("/api/exam/{eid}/blanks")
	public FillInTheBlanksExamQuestion createBlanksQuestion(@PathVariable("eid") int eid,
	                                                        @RequestBody FillInTheBlanksExamQuestion question) {
		Optional<Exam> opt = examRepo.findById(eid);
		if (opt.isPresent()) {
			Exam exam = opt.get();
			question.setExam(exam);
			return repo.save(question);
		}
		return null;
	}

	@PutMapping("/api/blanks/{id}")
	public FillInTheBlanksExamQuestion updateBlanksQuestion(@PathVariable("id") int id,
	                                                        @RequestBody FillInTheBlanksExamQuestion newQuestion) {
		Optional<FillInTheBlanksExamQuestion> opt = repo.findById(id);
		if (opt.isPresent()) {
			FillInTheBlanksExamQuestion oldQuestion = opt.get();
			newQuestion.setId(id);
			return repo.save(newQuestion);
		}
		throw new NoSuchElementException();
	}

	@GetMapping("/api/blanks/{id}")
	public FillInTheBlanksExamQuestion getBlanksQuestion(@PathVariable("id") int id) {
		return repo.findById(id).orElse(null);
	}

	@DeleteMapping("/api/blanks/{id}")
	public void deleteBlanksQuestion(@PathVariable("id") int id) {
		repo.deleteById(id);
	}
}
