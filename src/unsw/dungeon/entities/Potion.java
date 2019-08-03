package unsw.dungeon.entities;

import unsw.dungeon.Dungeon;

/**
 * The potion class that makes players invincible to enemies for ten seconds
 * @author Robert Clifton-Everest, Waqif Alam and Owen Silver
 *
 */
public class Potion extends Entity {

	public Potion(int x, int y) {
		super(x, y);
	}
	
	/**
	 * Check if a player is moving into a potion
	 */
	@Override
	public void squareBehav(Player p, String direction) {
		System.out.println("STEPPED ON A Potion");
		Dungeon dungeon = p.getDungeon();
		p.changeToPotionState();
		dungeon.removeEntity(this);
		setAlive(false);
	}
}
