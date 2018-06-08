package webdev.models.questions;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "JOINED_TRUE_OR_FALSE_QUESTION")
public class TrueOrFalseExamQuestion extends BaseExamQuestion {

	@Column(nullable = false)
	private Boolean correctAnswer;

	public TrueOrFalseExamQuestion() {
		super();
		this.correctAnswer = false;
		this.setQuestionType(QuestionType.TrueFalse);
	}

	public Boolean getCorrectAnswer() {
		return correctAnswer;
	}

	public void setCorrectAnswer(Boolean correctAnswer) {
		this.correctAnswer = correctAnswer;
	}
}
