package webdev.repositories.questions;

import org.springframework.data.repository.CrudRepository;

import webdev.models.questions.MultipleChoiceExamQuestion;

/**
 * Created by Michael Goodnow on 6/6/18.
 */

public interface MultipleChoiceQuestionRepository
	extends CrudRepository<MultipleChoiceExamQuestion, Integer> {
}
