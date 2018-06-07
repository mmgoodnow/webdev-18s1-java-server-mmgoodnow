package webdev.repositories.questions;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import webdev.models.questions.BaseExamQuestion;

public interface BaseQuestionRepository extends CrudRepository<BaseExamQuestion, Integer> {

	@Query("SELECT q FROM BaseExamQuestion q WHERE q.exam.id=:eid")
	Iterable<BaseExamQuestion> findAllByExamId(@Param("eid") int examId);
}
