package unsw.dungeon.entities;

import unsw.dungeon.Dungeon;

/**
 * An exit class to end the game if objectives are complete. 
 * @author Owen Silver and Waqif Alam
 *
 */
public class Exit extends Entity {
	private Dungeon dungeon;
	public Exit(Dungeon dungeon, int x, int y) {
		super(x, y);
		this.dungeon = dungeon;
	}
	
	/**
	 * Check if player is moving into an exit
	 * Complete objective if the player is moving into exit
	 */
	@Override
	public void squareBehav(Player p, String direction) {
		System.out.println("Found an exit");
		dungeon.completeExitObjective(dungeon.getObjective());
	}

}
