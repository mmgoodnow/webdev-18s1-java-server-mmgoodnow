package webdev.models.questions;

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

import webdev.models.widgets.Exam;

@Entity
@Table(name = "JOINED_BASE_QUESTION")
@Inheritance(strategy=InheritanceType.JOINED)
public class BaseExamQuestion {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private int points;
	private String title;
	private String description;

	@ManyToOne
	@JsonIgnore
	private Exam exam;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPoints() {
		return points;
	}
	public void setPoints(int points) {
		this.points = points;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public Exam getExam() {
		return exam;
	}

	public void setExam(Exam newExam) {
		if (this.exam != null) {
			if (this.exam.equals(newExam)) return;
			this.exam.removeQuestion(this);
		}
		this.exam = newExam;
		if (newExam != null) {
			newExam.addQuestion(this);
		}
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof BaseExamQuestion)) return false;
		BaseExamQuestion that = (BaseExamQuestion) o;
		return id == that.id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
}
