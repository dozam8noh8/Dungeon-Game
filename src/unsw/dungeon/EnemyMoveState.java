package unsw.dungeon;

public interface EnemyMoveState {
	public boolean move(int xDiff, int yDiff);
	public EnemyMoveState changeToChaseState();
	public EnemyMoveState changeToFleeState();
}
