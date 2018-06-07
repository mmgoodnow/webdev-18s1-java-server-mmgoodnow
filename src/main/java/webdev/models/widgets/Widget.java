package webdev.models.widgets;

import com.fasterxml.jackson.annotation.JsonIgnore;
import webdev.models.Lesson;

import javax.persistence.*;
import java.util.Objects;

/**
 * Created by Michael Goodnow on 5/30/18.
 */

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "WIDGET_NATIVE")
public abstract class Widget {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String title;

	private WidgetType widgetType;

	@ManyToOne
	@JsonIgnore
	private Lesson lesson;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Lesson getLesson() {
		return lesson;
	}

	public void setLesson(Lesson newLesson) {
		if (this.lesson != null) {
			if (this.lesson.equals(newLesson)) return;
			this.lesson.removeWidget(this);
		}
		this.lesson = newLesson;
		if (newLesson != null) {
			newLesson.addWidget(this);
		}
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Widget)) return false;
		Widget widget = (Widget) o;
		return id == widget.id;
	}

	public WidgetType getWidgetType() {
		return widgetType;
	}

	public void setWidgetType(WidgetType widgetType) {
		this.widgetType = widgetType;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}
