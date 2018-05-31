package webdev.repositories;

import org.springframework.data.repository.CrudRepository;

import webdev.models.Module;

/**
 * Created by Michael Goodnow on 5/21/18.
 */

public interface ModuleRepository extends CrudRepository<Module, Integer> {
}
