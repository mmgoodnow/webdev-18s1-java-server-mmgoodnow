package webdev.repositories.questions;

import org.springframework.data.repository.CrudRepository;

import webdev.models.questions.FillInTheBlanksExamQuestion;

public interface FillInTheBlanksQuestionRepository
	extends CrudRepository<FillInTheBlanksExamQuestion, Integer> {

}
