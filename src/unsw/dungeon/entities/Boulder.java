package unsw.dungeon.entities;

import java.util.ArrayList;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import unsw.dungeon.Dungeon;
import unsw.dungeon.Observer;
import unsw.dungeon.Subject;
/**
 * The Boulder class to represent a boulder in a game.
 * Boulders will be observed by Dungeon to check if they are pressed
 * on switches
 * @author Waqif Alam and Owen Silver
 *
 */
public class Boulder extends Entity implements Subject{
	private boolean canMove;
	private Dungeon dungeon;
	private ArrayList<Observer> observers = new ArrayList<Observer>();
	
	/**
	 * A boulder constructor
	 * @param dungeon
	 * @param x - x Coordinate
	 * @param y - y-Coordinate
	 */
	public Boulder(Dungeon dungeon, int x, int y) {
		super(x, y);
		this.canMove = true;
		this.dungeon = dungeon;
	}

	/**
	 * Function will be called when the player moves
	 * Checks if the player is trying to push the boulder
	 * Also checks if the boulder is being pushed into another boulder
	 */
	@Override
	public void squareBehav(Player p, String direction) {
		switch(direction){
		case "right":
			canMove = dungeon.makeMoveEntity(getX()+ 1, getY());
			moveTo(getX()+1, getY(), canMove, p);
			break;
		case "left":
			canMove = dungeon.makeMoveEntity(getX()- 1, getY());
			moveTo(getX()-1, getY(), canMove, p);
			break;
		case "up":
			canMove = dungeon.makeMoveEntity(getX(), getY()-1);
			moveTo(getX(), getY()-1, canMove, p);
			break;
		case "down":
			canMove = dungeon.makeMoveEntity(getX(), getY()+1);
			moveTo(getX(), getY()+1, canMove, p);
			break;
		
		}
	}
	/**
	 * This method will check if canMove is true, moving the entity and notifying observers.
	 * If canMove is false, it will not move and set player's canMove to false as well.
	 * @param x - the x coordinate being moved to
	 * @param y - the y coordinate being moved to
	 * @param canMove - whether the player canMove or not
	 * @param p - the player that triggered the potential move of the boulder
	 */
	public void moveTo(int x, int y, boolean canMove, Player p){
		if (canMove) {
			x().set(x);
            y().set(y);
            addObservers();
            notifyObservers();
		}
		else {
			p.setCanMove(false);
		}
	}
	
	/**
	 * Method to not allow another boulder to push the boulder
	 */
	@Override
	public boolean entityMoveThrough() {
		return false;
	}
	
	/**
	 * Method is called when a boulder or an enemy is trying to
	 * move into the boulder
	 */
	public void squareBehav(Entity e, String direction) {
		e.entityMoveThrough();
	}
	
	/**
	 * Add dungeon to the list of observers
	 */
	public void addObservers() {
		observers.add(dungeon);
	}
	
	/**
	 * Add observer to observer's list
	 */
	@Override
	public void registerObserver(Observer o) {
		observers.add(o);
	}

	/**
	 * Remove an observer from the observer's list
	 */
	@Override
	public void removeObserver(Observer o) {
		int i = observers.indexOf(o);
		if (i >= 0) {
			observers.remove(i);
		}
	}

	/**
	 * Notify observer when the boulder is moving
	 */
	@Override
	public void notifyObservers() {
		for (int i = 0; i < observers.size(); i++) {
			Observer observer = (Observer)observers.get(i);
			observer.update(this);
		}
	}
	
	/**
	 * Remove a boulder when a bomb hits the boulder
	 */
	public void killBoulder() {
		setAlive(false);
	}

}
