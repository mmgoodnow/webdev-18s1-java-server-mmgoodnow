package webdev.repositories;

import org.springframework.data.repository.CrudRepository;

import webdev.models.Widget;

/**
 * Created by Michael Goodnow on 5/30/18.
 */

public interface WidgetRepository extends CrudRepository<Widget, Integer> {

}
