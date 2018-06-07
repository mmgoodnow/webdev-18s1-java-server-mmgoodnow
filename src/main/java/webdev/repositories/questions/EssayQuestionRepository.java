package webdev.repositories.questions;

import org.springframework.data.repository.CrudRepository;

import webdev.models.questions.EssayExamQuestion;

/**
 * Created by Michael Goodnow on 6/6/18.
 */

public interface EssayQuestionRepository extends CrudRepository<EssayExamQuestion, Integer> {
}
