package unsw.dungeon.entities;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
/**
 * The Treasure object that player can collect throughout the game
 * @author Robert Clifton-Everest, Waqif Alam and Owen Silver
 *
 */
import unsw.dungeon.Dungeon;

public class Treasure extends Entity{
	
	public Treasure(int x, int y) {
		super(x, y);
	}
	
	/**
	 * Checks if a player is moving and collecting treasure
	 * Method will also complete the treasure objective if all treasure is collected
	 */
	@Override
	public void squareBehav(Player p, String direction) {
		System.out.println("STEPPED ON AN Treasure");
		Dungeon dungeon = p.getDungeon();
		p.addTreasure(this);
		dungeon.removeEntity(this);
		setAlive(false);
		for (Entity e : dungeon.getEntities()) { //not complete
			if (e instanceof Treasure) {
				return;
			}
		}
		dungeon.completeTreasureObjective(dungeon.getObjective()); //should loop through alltreasures! FIX
		setAlive(false);
	}

}
