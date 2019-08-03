package unsw.dungeon.entities;

import java.util.ArrayList;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import unsw.dungeon.Dungeon;
import unsw.dungeon.NoPotionState;
import unsw.dungeon.Observer;
import unsw.dungeon.PotionStatePlayer;
import unsw.dungeon.Subject;

/**
 * The enemy entity class chases the player, moving every 3 moves the player makes.
 * It can only move up, down, left or right. If a player touches an enemy the player dies.
 * These effects are reversed if a player is under the influence of a potion.
 * @author Owen Silver and Waqif Alam
 *
 */
public class Enemy extends Entity implements Observer {
	private Dungeon dungeon; //Dungeon the Enemy is contained in
	private int moveCounter; //Counts every 3 moves of the player
	private BooleanProperty alive; //Whether the enemy should be displayed and methods work.
	private boolean canMove;
	
	/**
	 * Instantiates an enemy.
	 * @param dungeon - the dungeon the enemy is located within
	 * @param x - the x coordinate the enemy currently lies
	 * @param y - the y coordinate the enemy currently lies
	 */
	public Enemy(Dungeon dungeon, int x, int y) {
		super(x, y);
		this.dungeon = dungeon;
		this.moveCounter = 0;
		this.alive = new SimpleBooleanProperty(true);
		this.canMove = true;
	}
	/**
	 * Returns whether the enemy can be considered dead (false) or alive (true)
	 * @return - boolean of whether enemy is alive.
	 */
	public boolean getAlive() {
		return this.alive.getValue();
	}
	
	/**
	 * Returns if enemy is alive or not
	 * @return alive - BooleanProperty to find if enemy is alive or not
	 */
	@Override
	public BooleanProperty isAlive() {
		return this.alive;
	}

	/**
	 * If a player steps on an enemy, they are killed, unless they are
	 * under the influence of a potion, in which the enemy is killed.
	 */
	@Override
	public void squareBehav(Player p, String direction) {
		if (alive.getValue()) {
			if (p.getPotionState() instanceof NoPotionState) {
				p.killPlayer();
			} else {
				this.killEnemy();

			}
		}
	}

	/**
	 * Update method called every time a player moves.
	 * Increases a counter, after 3 increases the enemy will move towards the player.
	 */
	@Override
	public void update(Subject o) {
		if (this.alive.getValue()) {
			this.moveCounter ++;
			if (this.moveCounter == 3) {
				moveTowardsPlayer();
				this.moveCounter = 0;
			}
		}
	}
	/**
	 * This method calculates an x and y direction that an enemy should
	 * move in order to catch the player. If the player is under the effects
	 * of the potion, the direction will be reversed by multiplying changes by -1.
	 * The priority of move direction is decided by which distance is larger (x or y)
	 * between enemy and player. If the first priority direction cannot be moved to
	 * the second priority will be tried. If neither of these are possible, the enemy
	 * will not move.
	 */
	public void moveTowardsPlayer() {
		int xDiff = dungeon.getPlayer().getX() - this.getX();
		int yDiff = dungeon.getPlayer().getY() - this.getY();
		boolean potion = false;
		int xChange = 1;
		int yChange = 1;
		if (dungeon.getPlayer().getPotionState() instanceof PotionStatePlayer) {
			potion = true;
		}
		boolean moved = false;
		if (xDiff < 0) { //change will be left
			xChange = -1;
		}
		else if (xDiff > 0) { //change will be right
			xChange = 1;
		}
		
		if (yDiff < 0) { //change will be up
			yChange = -1;
		}
		else if (yDiff > 0) { //change will be down
			yChange = 1;
		}
		if (potion) {
			xChange = xChange * -1;
			yChange = yChange * -1;
		}
		if (Math.abs(xDiff) >= Math.abs(yDiff)) {
			moved = move(xChange, 0);
			if (!moved) {
				move(0,yChange);
			}
		}
		else {
			moved = move(0, yChange);
			if (!moved) {
				move(xChange, 0);
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
		if (dungeon.makeMoveEntity(x, y)) {
			x().set(x);
			y().set(y);
			return true;
		}
		return false;
	}
	
	/**
	 * Move enemy by xChange in the x-coordinate
	 * @param xChange - the change in x-coordinate for the enemy
	 * @return true if enemy can be moved, false if enemy cannot be moved
	 */
	public boolean moveX(int xChange) {
		int x = getX() + xChange;
		int y = getY();
		if (dungeon.makeMoveEntity(x, y)) {
			x().set(x);
			y().set(y);
			return true;
		}
		return false;
	}
	
	/**
	 * Checking if enemy can be moved by yChange in the y-coordinate
	 * @param yChange - the change for y-coordinate
	 * @return true if enemy can be moved, false if cannot be moved
	 */
	public boolean moveY(int yChange) {
		int x = getX();
		int y = getY() + yChange;
		if (dungeon.makeMoveEntity(x, y)) {
			x().set(x);
			y().set(y);
			return true;
		}
		return false;
	}
	
	/**
	 * Kills the enemy, notifies players and completes the enemy objective for a dungeon if
	 * there are no more enemies.
	 */
	public void killEnemy() {
		this.alive.setValue(false);
		dungeon.removeEntity(this);
		for (Entity e : dungeon.getEntities()) {
			if (e instanceof Enemy) {
				return;
			}
		}
		
		dungeon.completeEnemyObjective(dungeon.getObjective());
	}
	
	/**
	 * Trying to stop another enemy or boulder to move into the enemy
	 */
	@Override
	public boolean entityMoveThrough() {
		return false;
	}

}