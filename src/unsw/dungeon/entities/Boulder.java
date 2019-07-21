package unsw.dungeon.entities;

import java.util.ArrayList;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import unsw.dungeon.Dungeon;
import unsw.dungeon.Observer;
import unsw.dungeon.Subject;

public class Boulder extends Entity implements Subject{
	private boolean canMove;
	private Dungeon dungeon;
	private boolean alive;
	private ArrayList<Observer> observers = new ArrayList<Observer>();
	
	public Boulder(Dungeon dungeon, int x, int y) {
		super(x, y);
		this.canMove = true;
		this.dungeon = dungeon;
		this.alive = true;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void squareBehav(Player p, String direction) {
		switch(direction){
		case "right":
			canMove = dungeon.makeMoveEntity(getX()+ 1, getY()); //doesnt check if edge of map
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
	@Override
	public boolean entityMoveThrough() {
		return false;
	}
	public void squareBehav(Entity e, String direction) {
		e.entityMoveThrough();
	}
	public void addObservers() {
		observers.add(dungeon);
	}
	
	@Override
	public void registerObserver(Observer o) {
		// TODO Auto-generated method stub
		observers.add(o);
	}

	@Override
	public void removeObserver(Observer o) {
		// TODO Auto-generated method stub
		int i = observers.indexOf(o);
		if (i >= 0) {
			observers.remove(i);
		}
	}

	@Override
	public void notifyObservers() {
		// TODO Auto-generated method stub
		for (int i = 0; i < observers.size(); i++) {
			Observer observer = (Observer)observers.get(i);
			observer.update(this);
		}
	}
	public void killBoulder() {
		this.alive = false;
	}

}
