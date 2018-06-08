package webdev.models.questions;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "JOINED_FILL_IN_THE_BLANK_QUESTION")
public class FillInTheBlanksExamQuestion extends BaseExamQuestion {

	private String blanks;

	public FillInTheBlanksExamQuestion() {
		super();
		this.blanks = "";
		this.setQuestionType(QuestionType.Blanks);
	}

	public String getBlanks() {
		return blanks;
	}

	public void setBlanks(String blanks) {
		this.blanks = blanks;
	}
}
