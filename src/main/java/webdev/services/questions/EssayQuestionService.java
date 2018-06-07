package webdev.services.questions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;
import java.util.Optional;

import webdev.models.questions.EssayExamQuestion;
import webdev.models.widgets.Exam;
import webdev.repositories.questions.EssayQuestionRepository;
import webdev.repositories.widgets.ExamRepository;

/**
 * Created by Michael Goodnow on 6/6/18.
 */

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class EssayQuestionService {

	@Autowired
	ExamRepository examRepo;

	@Autowired
	EssayQuestionRepository repo;

	@PostMapping("/api/exam/{eid}/essay")
	public EssayExamQuestion createEssayQuestion(@PathVariable("eid") int eid,
	                                             @RequestBody EssayExamQuestion question) {
		Optional<Exam> opt = examRepo.findById(eid);
		if (opt.isPresent()) {
			Exam exam = opt.get();
			question.setExam(exam);
			return repo.save(question);
		}
		return null;
	}

	@PutMapping("/api/essay/{id}")
	public EssayExamQuestion updateEssayQuestion(@PathVariable("id") int id,
	                                             @RequestBody EssayExamQuestion newQuestion) {
		Optional<EssayExamQuestion> opt = repo.findById(id);
		if (opt.isPresent()) {
			EssayExamQuestion oldQuestion = opt.get();
			newQuestion.setId(id);
			return repo.save(newQuestion);
		}
		throw new NoSuchElementException();
	}

	@GetMapping("/api/essay/{id}")
	public EssayExamQuestion getEssayQuestion(@PathVariable("id") int id) {
		return repo.findById(id).orElse(null);
	}

	@DeleteMapping("/api/essay/{id}")
	public void deleteEssayQuestion(@PathVariable("id") int id) {
		repo.deleteById(id);
	}
}
