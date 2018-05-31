package webdev.repositories;

import org.springframework.data.repository.CrudRepository;

import webdev.models.Lesson;

/**
 * Created by Michael Goodnow on 5/21/18.
 */

public interface LessonRepository extends CrudRepository<Lesson, Integer> {
}
