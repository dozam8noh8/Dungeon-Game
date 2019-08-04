package unsw.dungeon;

import unsw.dungeon.entities.Enemy;

public class EnemyFleeState implements EnemyMoveState{
	private Enemy enemy;
	
	public EnemyFleeState(Enemy enemy) {
		this.enemy = enemy;
	}
		
	@Override
	/**
	 * Trying to move the enemy to another square in game
	 * @param xChange - the change of x-coordinate for the move
	 * @param yChange - the change of y-coordinate for the move
	 * @return true if enemy can move, or false if enemy cannot move
	 */
	public boolean move(int xChange, int yChange) {
		if (xChange == 0 && yChange == 0)return false;
		int x = enemy.getX() - xChange;
		int y = enemy.getY() - yChange;
		if (enemy.getDungeon().makeMoveEntity(x, y)) {
			enemy.x().set(x);
			enemy.y().set(y);
			return true;
		}
		return false;
	}
	@Override
	public EnemyMoveState changeToChaseState() {
		return new EnemyChaseState(this.enemy);
	}

	@Override
	public EnemyMoveState changeToFleeState() {
		return this;
	}

	
}
