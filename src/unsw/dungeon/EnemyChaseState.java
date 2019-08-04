package unsw.dungeon;

import unsw.dungeon.entities.Enemy;

public class EnemyChaseState implements EnemyMoveState {
	private Enemy enemy;
	
	public EnemyChaseState(Enemy enemy) {
		this.enemy = enemy;
	}
	@Override
	public boolean move(int xChange, int yChange) {
		if (xChange == 0 && yChange == 0)return false;
		int x = enemy.getX() + xChange;
		int y = enemy.getY() + yChange;
		if (enemy.getDungeon().makeMoveEntity(x, y)) {
			enemy.x().set(x);
			enemy.y().set(y);
			return true;
		}
		return false;
	}
	@Override
	public EnemyMoveState transition() {
		return new EnemyFleeState(this.enemy);
	}

}
