package com.example.coursemanagement.repositories;

import com.example.coursemanagement.models.Course;

import org.springframework.data.repository.CrudRepository;

/**
 * Created by Michael Goodnow on 5/21/18.
 */

public interface CourseRepository extends CrudRepository<Course, Integer> {

}
