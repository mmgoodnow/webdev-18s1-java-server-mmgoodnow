package com.example.coursemanagement.services;

import com.example.coursemanagement.repositories.LessonRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Michael Goodnow on 5/21/18.
 */

@RestController
public class LessonService {
	@Autowired
	LessonRepository repo;
}
