package unsw.dungeon.entities;

import unsw.dungeon.Dungeon;
import unsw.dungeon.Observer;
import unsw.dungeon.Subject;
import unsw.dungeon.entities.Player;

/**
 * An exit class to end the game if objectives are complete. 
 * @author Owen Silver and Waqif Alam
 *
 */
public class Exit extends Entity implements Observer {
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
		dungeon.completeExitObjective(dungeon.getObjective());
	}

	/**
	 * Let the dungeon know that the player is moving into an exit
	 */
	@Override
	public void update(Subject o) {
		int playerX = ((Player)o).getX();
		int playerY = ((Player)o).getY();
		if (playerX == this.getX() && playerY == this.getY()) {
			dungeon.completeExitObjective(dungeon.getObjective());
		}
		else {
			dungeon.resetExitObjective(dungeon.getObjective());
		}
		
	}


}
