package webdev.repositories;

import org.springframework.data.repository.CrudRepository;

import webdev.models.Course;

/**
 * Created by Michael Goodnow on 5/21/18.
 */

public interface CourseRepository extends CrudRepository<Course, Integer> {

}
