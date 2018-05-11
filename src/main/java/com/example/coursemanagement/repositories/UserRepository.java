package com.example.coursemanagement.repositories;

import com.example.coursemanagement.models.User;

import org.springframework.data.repository.CrudRepository;

/**
 * Created by Michael Goodnow on 5/10/18.
 */

public interface UserRepository extends CrudRepository<User, Integer> {

}
