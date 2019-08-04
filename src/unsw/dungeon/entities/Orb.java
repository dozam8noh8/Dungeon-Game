package unsw.dungeon.entities;

import unsw.dungeon.Dungeon;

public class Orb extends Entity {

	public Orb(int x, int y) {
		super(x, y);
	}
	@Override
	public void squareBehav(Player p, String direction) {
		Dungeon dungeon = p.getDungeon();
		p.incrementOrb();
		dungeon.removeEntity(this);
		setAlive(false);
	}

}
