package webdev.repositories.questions;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import webdev.models.questions.BaseExamQuestion;
import webdev.models.widgets.Exam;

public interface BaseQuestionRepository extends CrudRepository<BaseExamQuestion, Integer>{

	@Query("SELECT q FROM BaseExamQuestion q WHERE q.exam_id=:eid")
	Iterable<BaseExamQuestion> findAllByExamId(@Param("eid") int examId);
}
