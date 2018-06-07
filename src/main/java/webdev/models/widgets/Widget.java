package webdev.models.widgets;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import webdev.models.Lesson;

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
	private String text;

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

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
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
}
