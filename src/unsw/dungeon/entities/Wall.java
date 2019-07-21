package unsw.dungeon.entities;

/**
 * The Wall object represents walls in games where players or other entities
 * cannot enter
 * @author Robert Clifton-Everest, Waqif Alam and Owen Silver
 *
 */
public class Wall extends Entity {

    public Wall(int x, int y) {
        super(x, y);
    }

    /**
     * Check if a player is trying to move into a wall and stop player from moving
     */
	@Override
	public void squareBehav(Player p, String direction) {
		p.setCanMove(false);
	}
    
	/**
	 * Do not allow enemies and boulders to move into walls
	 */
	@Override
	public boolean entityMoveThrough() {
		return false;
	}
}
