package webdev.repositories;

import webdev.models.Module;

import org.springframework.data.repository.CrudRepository;

/**
 * Created by Michael Goodnow on 5/21/18.
 */

public interface ModuleRepository extends CrudRepository<Module, Integer> {
}
