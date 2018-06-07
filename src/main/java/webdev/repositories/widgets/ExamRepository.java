package webdev.repositories.widgets;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import webdev.models.widgets.Assignment;
import webdev.models.widgets.Exam;

public interface ExamRepository extends CrudRepository<Exam, Integer>{
	@Query("SELECT e FROM Exam e WHERE e.lesson_id=:lid")
	Iterable<Exam> findAllByLessonId(@Param("lid") int lessonId);
}
