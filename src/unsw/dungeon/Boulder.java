package unsw.dungeon;

import java.util.ArrayList;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;

public class Boulder extends Entity implements Subject{
	boolean canMove;
	Dungeon dungeon;
	private ArrayList observers = new ArrayList();
	
	public Boulder(Dungeon dungeon, int x, int y) {
		super(x, y);
		this.canMove = true;
		this.dungeon = dungeon;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void squareBehav(Player p, String direction) {
		switch(direction){
		case "right":
			canMove = dungeon.makeMoveBoulder(getX()+ 1, getY(), this); //doesnt check if edge of map
			if (canMove) {
	            x().set(getX() + 1);
	            addObservers();
			}
			else {
				p.setCanMove(false);
			}
			break;
		case "left":
			canMove = dungeon.makeMoveBoulder(getX()- 1, getY(), this);
			if (canMove) {
	            x().set(getX() - 1);
	            addObservers();
			}
			else {
				p.setCanMove(false);
			}
			break;
		case "up":
			canMove = dungeon.makeMoveBoulder(getX(), getY()-1, this);
			if (canMove) {
	            y().set(getY() - 1);
	            addObservers();
			}
			else {
				p.setCanMove(false);
			}
			break;
		case "down":
			canMove = dungeon.makeMoveBoulder(getX(), getY()+1, this);
			if (canMove) {
	            y().set(getY() + 1);
	            addObservers();
			}
			else {
				p.setCanMove(false);
			}
			break;
		
		}
	}

	public void addObservers() {
		for (Entity e : dungeon.getEntities()) {
			if (e instanceof PPlate) {
				if (e.getX() == getX() && e.getY()==getY()) {
					if (!observers.contains(e)) {
						registerObserver((Observer) e);
						System.out.println("On PPlate");
					}
				}
			}
		}
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

}
