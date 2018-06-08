package webdev.models.questions;


import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Michael Goodnow on 6/6/18.
 */

@Entity
@Table(name = "JOINED_ESSAY_QUESTION")
public class EssayExamQuestion extends BaseExamQuestion {
	public EssayExamQuestion() {
		super();
		this.setQuestionType(QuestionType.Essay);
	}
}
