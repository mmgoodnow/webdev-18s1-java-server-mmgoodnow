package webdev.services.widgets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;

import webdev.models.Lesson;
import webdev.models.widgets.Widget;
import webdev.repositories.LessonRepository;
import webdev.repositories.widgets.WidgetRepository;

/**
 * Created by Michael Goodnow on 5/30/18.
 */

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class WidgetService {

	@Autowired
	WidgetRepository repo;

	@Autowired
	LessonRepository lessonRepo;

	@GetMapping("/api/widget")
	public List<Widget> findAllWidgets() {
		return (List<Widget>) repo.findAll();
	}

	@GetMapping("/api/widget/{widgetId}")
	public Widget findWidgetById(@PathVariable("widgetId") int id) {
		return repo.findById(id).orElse(null);
	}

	@GetMapping("/api/lesson/{lessonId}/widget")
	public Set<Widget> findAllWidgetsForLesson(@PathVariable("lessonId") int lid) {
		Optional<Lesson> opt = lessonRepo.findById(lid);
		return opt.map(Lesson::getWidgets).orElse(null);
	}

	@PostMapping("/api/lesson/{lessonId}/widget")
	public Widget createWidget(@PathVariable("lessonId") int lid, @RequestBody Widget widget) {
		Optional<Lesson> opt = lessonRepo.findById(lid);
		if (opt.isPresent()) {
			Lesson lesson = opt.get();
			widget.setLesson(lesson);
			return repo.save(widget);
		}
		return null;
	}

	@PostMapping("/api/lesson/{lessonId}/widget/save")
	public void saveWidgetsForLesson(@PathVariable("lessonId") int lid,
	                                    @RequestBody List<? extends Widget> widgets) {
		Lesson lesson = lessonRepo.findById(lid).orElseThrow(NoSuchElementException::new);
		lesson.getWidgets().clear();
		widgets.forEach(w -> w.setLesson(lesson));
		lessonRepo.save(lesson);
	}

	@PutMapping("/api/widget/{widgetId}")
	public Widget updateWidget(@PathVariable("widgetId") int id, @RequestBody Widget newWidget)
		throws NoSuchElementException {
		Optional<Widget> opt = repo.findById(id);
		if (opt.isPresent()) {
			newWidget.setId(id);
			return repo.save(newWidget);
		}
		throw new NoSuchElementException();
	}

	@DeleteMapping("/api/widget/{widgetId}")
	public void deleteWidget(@PathVariable("widgetId") int id) {
		repo.deleteById(id);
	}
}
