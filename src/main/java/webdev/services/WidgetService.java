package webdev.services;

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
import java.util.stream.Collectors;

import webdev.models.Lesson;
import webdev.models.Widget;
import webdev.repositories.LessonRepository;
import webdev.repositories.WidgetRepository;

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
	public List<Widget> findAllWidgetsForLesson(@PathVariable("lessonId") int lid) {
		Optional<Lesson> opt = lessonRepo.findById(lid);
		List<Widget> ret = opt.map(Lesson::getWidgets).orElse(null);
		return ret;
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
	public boolean saveWidgetsForLesson(@PathVariable("lessonId") int lid,
	                                    @RequestBody List<Widget> widgets) {
		Lesson lesson = lessonRepo.findById(lid).orElseThrow(NoSuchElementException::new);

		List<Widget> oldies = this.findAllWidgetsForLesson(lid);
		List<Widget> c = oldies
			.stream()
			.filter(ow -> widgets.stream()
				.noneMatch(nw -> ow.getId() == nw.getId())).collect(Collectors.toList());
		List<Integer> ids = c.stream()
			.map(Widget::getId).collect(Collectors.toList());

			for (int id : ids) {
				Optional<Widget> w = repo.findById(id);
					repo.deleteById(id);
			}
			List<Widget> currentBackEnd = (List<Widget>) repo.findAll();

		for (Widget newWidget : widgets) {
			try {
				this.updateWidget(newWidget.getId(), newWidget);
			} catch (NoSuchElementException e) {
				this.createWidget(lid, newWidget);
			}
		}
		return true;
	}

	@PutMapping("/api/widget/{widgetId}")
	public Widget updateWidget(@PathVariable("widgetId") int id, @RequestBody Widget newWidget)
		throws NoSuchElementException {
		Optional<Widget> opt = repo.findById(id);
		if (opt.isPresent()) {
			Widget widget = opt.get();
			widget.setClassName(newWidget.getClassName());
			widget.setHeight(newWidget.getHeight());
			widget.setHref(newWidget.getHref());
			widget.setListItems(newWidget.getListItems());
			widget.setListType(newWidget.getListType());
			widget.setName(newWidget.getName());
			widget.setPosition(newWidget.getPosition());
			widget.setSize(newWidget.getSize());
			widget.setSrc(newWidget.getSrc());
			widget.setStyle(newWidget.getStyle());
			widget.setText(newWidget.getText());
			widget.setWidgetType(newWidget.getWidgetType());
			widget.setWidth(newWidget.getWidth());
			return repo.save(widget);
		}
		throw new NoSuchElementException();
	}

	@DeleteMapping("/api/widget/{widgetId}")
	public void deleteWidget(@PathVariable("widgetId") int id) {
		repo.deleteById(id);
	}
}
