package webdev.models.widgets;

import javax.persistence.Entity;

/**
 * Created by Michael Goodnow on 6/6/18.
 */

@Entity
public class Assignment extends Widget {
	private int points;
	private String title;
	private String description;


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
}
