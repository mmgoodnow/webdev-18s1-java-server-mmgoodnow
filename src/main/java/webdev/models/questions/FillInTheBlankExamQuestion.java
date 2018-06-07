package webdev.models.questions;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "JOINED_FILL_IN_THE_BLANK_QUESTION")
public class FillInTheBlankExamQuestion extends BaseExamQuestion {
	@Column(name = "BLANKS", nullable = false)
	private String blanks;
	public String getBlanks() {
		return blanks;
	}
	public void setBlanks(String blanks) {
		this.blanks = blanks;
	}
}
