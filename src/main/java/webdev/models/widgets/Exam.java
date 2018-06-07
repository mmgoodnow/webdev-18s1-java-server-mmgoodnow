package webdev.models.widgets;

import com.fasterxml.jackson.annotation.JsonIgnore;
import webdev.models.questions.BaseExamQuestion;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Exam extends Widget {

	@OneToMany(mappedBy = "exam", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnore
	private List<BaseExamQuestion> questions;


	public Exam() {
		super();
		this.setWidgetType(WidgetType.EXAM);
	}

	public List<BaseExamQuestion> getQuestions() {
		return questions;
	}

	public void setQuestions(List<BaseExamQuestion> questions) {
		this.questions = questions;
	}

	public void addQuestion(BaseExamQuestion q) {
		this.questions.add(q);
		q.setExam(this);
	}

	public void removeQuestion(BaseExamQuestion q) {
		this.questions.remove(q);
		q.setExam(null);
	}

}
