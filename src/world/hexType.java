package world;

import javafx.scene.paint.Color;

/**
 * 
 * This class implements all the types of hexes the map can contain.
 * 
 * Currently the only values are a label and a movement value.
 * 
 * @author Peter
 *
 */

public class hexType {
	
	private int movement;
	
	private String label;
	
	private javafx.scene.paint.Color color;
	/**
	 * Default constructor creates a "Plains" tile which has a movement cost of 1. Higher value means more difficult to cross.
	 * Except for 0 which means not crossable.
	 */
	public hexType(){

		this.setMovement(1);
		this.setLabel("Plains");
		this.setColor(Color.YELLOW);
	}

	public int getMovement() {
		return movement;
	}

	public void setMovement(int movement) {
		this.movement = movement;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(javafx.scene.paint.Color red) {
		this.color = red;
	}

}
