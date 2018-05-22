package com.example.coursemanagement.repositories;

import com.example.coursemanagement.models.Module;

import org.springframework.data.repository.CrudRepository;

/**
 * Created by Michael Goodnow on 5/21/18.
 */

public interface ModuleRepository extends CrudRepository<Module, Integer> {
}
