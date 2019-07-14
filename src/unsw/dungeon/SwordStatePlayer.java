package unsw.dungeon;

import java.util.ArrayList;

public class SwordStatePlayer implements SwordState {

	@Override
	public SwordState changeToSwordState() {
		// TODO Auto-generated method stub
		return this;
	}

	@Override
	public SwordState changeToNoSwordState() {
		// TODO Auto-generated method stub
		return new NoSwordState();
	}

	@Override
	public void attack(Player player) {
		// TODO Auto-generated method stub
		System.out.println("USING SWORD");
		Dungeon dungeon = player.getDungeon();
		int x = player.getX();
		int y = player.getY();
		if (x> 0) {
			ArrayList<Entity> entOnSq = dungeon.getEntOnSq(x-1, y);
			for (Entity e : entOnSq) {
				if (e instanceof Enemy) {
					dungeon.removeEntity(e);
					System.out.println("Killed enemy");
				}
			}
		}
		if (y > 0) {
			ArrayList<Entity> entOnSq = dungeon.getEntOnSq(x, y-1);
			for (Entity e : entOnSq) {
				if (e instanceof Enemy) {
					dungeon.removeEntity(e);
					System.out.println("Killed enemy");
				}
			}
		}
		if (x < dungeon.getWidth() - 1) {
			ArrayList<Entity> entOnSq = dungeon.getEntOnSq(x+1, y);
			for (Entity e : entOnSq) {
				if (e instanceof Enemy) {
					dungeon.removeEntity(e);
					System.out.println("Killed enemy");
				}
			}
		}
		if (y < dungeon.getHeight() - 1) {
			ArrayList<Entity> entOnSq = dungeon.getEntOnSq(x, y+1);
			for (Entity e : entOnSq) {
				if (e instanceof Enemy) {
					dungeon.removeEntity(e);
					System.out.println("Killed enemy");
				}
			}
		}
	}

}
