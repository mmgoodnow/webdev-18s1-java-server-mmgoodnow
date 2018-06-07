package webdev.models.widgets;

import javax.persistence.Entity;

/**
 * Created by Michael Goodnow on 6/6/18.
 */

@Entity
public class Assignment extends Widget {
	private int points;
	private String description;

	public Assignment() {
		super();
		this.setWidgetType(WidgetType.ASSIGNMENT);
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
