package webdev.services.widgets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import webdev.models.questions.BaseExamQuestion;
import webdev.repositories.questions.BaseQuestionRepository;

/**
 * Created by Michael Goodnow on 6/6/18.
 */

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class BaseQuestionService {
	@Autowired
	BaseQuestionRepository repo;

	@GetMapping("/api/question")
	public List<BaseExamQuestion> findAllQuestions() {
		return (List<BaseExamQuestion>) repo.findAll();
	}

	@GetMapping("/api/exam/{eid}/question")
	public List<BaseExamQuestion> findAllQuestionsForExam(@PathVariable("eid") int eid) {
		return (List<BaseExamQuestion>) repo.findAllByExamId(eid);
	}

}
