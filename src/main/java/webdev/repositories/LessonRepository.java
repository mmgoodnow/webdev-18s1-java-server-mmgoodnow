package webdev.repositories;

import webdev.models.Lesson;

import org.springframework.data.repository.CrudRepository;

/**
 * Created by Michael Goodnow on 5/21/18.
 */

public interface LessonRepository extends CrudRepository<Lesson, Integer> {
}
