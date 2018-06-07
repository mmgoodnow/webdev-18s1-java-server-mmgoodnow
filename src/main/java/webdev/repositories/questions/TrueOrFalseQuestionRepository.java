package webdev.repositories.questions;

import org.springframework.data.repository.CrudRepository;

import webdev.models.questions.TrueOrFalseExamQuestion;

public interface TrueOrFalseQuestionRepository
	extends CrudRepository<TrueOrFalseExamQuestion, Integer> {
}
