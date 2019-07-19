package unsw.dungeon;

import java.util.ArrayList;

public class Sword extends Entity implements Weapon{
	int attacks = 5;
	private Player p;
	
	public Sword(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void squareBehav(Player p, String direction) {
		System.out.println("STEPPED ON AN Sword");
		Dungeon dungeon = p.getDungeon();
		dungeon.removeEntity(this);
		p.setWeapon(this);
	}

	@Override
	public void attack(Player p) {
		// TODO Auto-generated method stub
				if (attacks < 1) {
					p.setWeapon(null);
					return;
				}
				System.out.println("USING SWORD");
				int x = p.getX();
				int y = p.getY();
				Dungeon dungeon = p.getDungeon();
				if (x> 0) {
					ArrayList<Entity> entOnSq = dungeon.getEntOnSq(x-1, y);
					for (Entity e : entOnSq) {
						if (e instanceof Enemy) {
							dungeon.removeEntity(e);
							((Enemy) e).killEnemy();
							System.out.println("Killed enemy");
						}
					}
				}
				if (y > 0) {
					ArrayList<Entity> entOnSq = dungeon.getEntOnSq(x, y-1);
					for (Entity e : entOnSq) {
						if (e instanceof Enemy) {
							dungeon.removeEntity(e);
							((Enemy) e).killEnemy();
							System.out.println("Killed enemy");
						}
					}
				}
				if (x < dungeon.getWidth() - 1) {
					ArrayList<Entity> entOnSq = dungeon.getEntOnSq(x+1, y);
					for (Entity e : entOnSq) {
						if (e instanceof Enemy) {
							dungeon.removeEntity(e);
							((Enemy) e).killEnemy();
							System.out.println("Killed enemy");
						}
					}
				}
				if (y < dungeon.getHeight() - 1) {
					ArrayList<Entity> entOnSq = dungeon.getEntOnSq(x, y+1);
					for (Entity e : entOnSq) {
						if (e instanceof Enemy) {
							((Enemy) e).killEnemy();
							dungeon.removeEntity(e);
							System.out.println("Killed enemy");
						}
					}
				}
				attacks --;
			

	}
}