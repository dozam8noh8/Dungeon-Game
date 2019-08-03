package unsw.dungeon.entities;

import java.util.ArrayList;

import unsw.dungeon.Dungeon;
import unsw.dungeon.Subject;

public class BossEnemy extends Enemy{

	public BossEnemy(Dungeon dungeon, int x, int y) {
		super(dungeon, x, y);
	}
	
	/**
	 * Update method called every time a player moves.
	 * Increases a counter, after 2 increases the enemy will move towards the player.
	 */
	@Override
	public void update(Subject o) {
		if (isAlive().getValue()) {
			this.setMoveCounter(this.getMoveCounter() + 1);
			if (this.getMoveCounter() == 2) {
				moveTowardsPlayer();
				this.setMoveCounter(0);
			}
		}
	}
	
	/**
	 * Trying to move the enemy to another square in game
	 * @param xChange - the change of x-coordinate for the move
	 * @param yChange - the change of y-coordinate for the move
	 * @return true if enemy can move, or false if enemy cannot move
	 */
	public boolean move(int xChange, int yChange) {
		if (xChange == 0 && yChange == 0)return false;
		int x = getX() + xChange;
		int y = getY() + yChange;
		if (getDungeon().makeMoveEntity(x, y)) {
			x().set(x);
			y().set(y);
			killPlayerAdjacent(x, y);
			return true;
		}
		return false;
	}

	private void killPlayerAdjacent(int x, int y) {
		Dungeon dungeon = getDungeon();
		if (x < dungeon.getWidth()) {
			bossKillsPlayer(x+1, y, dungeon);
		}
		if (x > 0) {
			bossKillsPlayer(x-1, y, dungeon);
		}
		if (y > 0) {
			bossKillsPlayer(x, y-1, dungeon);
		}
		if (y < dungeon.getHeight()) {
			bossKillsPlayer(x, y+1, dungeon);
		}
	}

	private void bossKillsPlayer(int x, int y, Dungeon dungeon) {
		ArrayList<Entity> entOnSq = dungeon.getEntOnSq(x, y);
		for (Entity e: entOnSq) {
			if (e instanceof Player) {
				getPlayer().killPlayer();
			}
		}
	}

}
