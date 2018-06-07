package webdev.repositories.widgets;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import webdev.models.widgets.Assignment;

/**
 * Created by Michael Goodnow on 6/6/18.
 */

public interface AssignmentRepository extends CrudRepository<Assignment, Integer> {
	@Query("SELECT a FROM Assignment a WHERE a.lesson.id=:lid")
	Iterable<Assignment> findAllByLessonId(@Param("lid") int lessonId);

}
