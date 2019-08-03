package unsw.dungeon.entities;

import unsw.dungeon.Dungeon;

public class Life extends Entity {

	public Life(int x, int y) {
		super(x, y);
	}
	
	@Override
	public void squareBehav(Player p, String direction) {
		p.incrementLives();
		Dungeon d = p.getDungeon();
		setAlive(false);
		d.removeEntity(this);
	}
	
}
