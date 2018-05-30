package webdev.repositories;

import webdev.models.Course;

import org.springframework.data.repository.CrudRepository;

/**
 * Created by Michael Goodnow on 5/21/18.
 */

public interface CourseRepository extends CrudRepository<Course, Integer> {

}
