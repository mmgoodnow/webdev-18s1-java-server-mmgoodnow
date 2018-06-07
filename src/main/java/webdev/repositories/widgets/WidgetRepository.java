package webdev.repositories.widgets;

import org.springframework.data.repository.CrudRepository;

import webdev.models.widgets.Widget;

/**
 * Created by Michael Goodnow on 5/30/18.
 */

public interface WidgetRepository extends CrudRepository<Widget, Integer> {
}
