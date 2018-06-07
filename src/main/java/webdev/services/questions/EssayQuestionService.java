package webdev.services.questions;

import org.springframework.web.bind.annotation.*;

import webdev.models.questions.EssayExamQuestion;

/**
 * Created by Michael Goodnow on 6/6/18.
 */

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class EssayQuestionService {

	@PostMapping("/api/exam/{eid}/essay")
	public EssayExamQuestion createEssayQuestion(@PathVariable("eid") int eid,
	                                             @RequestBody EssayExamQuestion e) {

	}

	@PutMapping("/api/essay/{id}")
	public EssayExamQuestion updateEssayQuestion(@PathVariable("id") int id) {

	}

	@GetMapping("/api/essay/{id}")
	public EssayExamQuestion getEssayQuestion(@PathVariable("id") int id) {

	}

	@DeleteMapping("/api/essay/{id}")
	public void deleteEssayQuestion(@PathVariable("id") int id) {

	}
}
