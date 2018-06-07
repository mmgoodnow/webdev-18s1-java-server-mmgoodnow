package webdev.repositories.questions;

import org.springframework.data.repository.CrudRepository;

import webdev.models.questions.BaseExamQuestion;

public interface BaseQuestionRepository extends CrudRepository<BaseExamQuestion, Integer>{
}
