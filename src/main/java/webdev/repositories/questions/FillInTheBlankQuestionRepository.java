package webdev.repositories.questions;

import org.springframework.data.repository.CrudRepository;

import webdev.models.questions.FillInTheBlankExamQuestion;

public interface FillInTheBlankQuestionRepository
	extends CrudRepository<FillInTheBlankExamQuestion, Integer>{

}
