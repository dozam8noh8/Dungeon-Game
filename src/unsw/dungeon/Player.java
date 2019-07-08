package unsw.dungeon;

import java.util.ArrayList;

/**
 * The player entity
 * @author Robert Clifton-Everest
 *
 */
public class Player extends Entity implements Subject {

    private Dungeon dungeon;
    ArrayList<Observer> listObservers = new ArrayList<Observer>();

    /**
     * Create a player positioned in square (x,y)
     * @param x
     * @param y
     */
    public Player(Dungeon dungeon, int x, int y) {
        super(x, y);
        this.dungeon = dungeon;
    }

    public void moveUp() {
    	dungeon.findObservers();
    	String prev = "up";
        if (getY() > 0) {
            y().set(getY() - 1);
        }
        notifyObservers(prev);
    }

    public void moveDown() {
    	dungeon.findObservers();
    	String prev = "down";
        if (getY() < dungeon.getHeight() - 1) {
            y().set(getY() + 1);
        }
        notifyObservers(prev);
    }

    public void moveLeft() {
    	dungeon.findObservers();
    	String prev = "left";
        if (getX() > 0) {
            x().set(getX() - 1);
        }
        notifyObservers(prev);
    }

    public void moveRight() {
    	dungeon.findObservers();
    	String prev = "right";
        if (getX() < dungeon.getWidth() - 1) {
            x().set(getX() + 1);
        }
        notifyObservers(prev);
    }

	@Override
	public void registerObserver(Observer o) {
		if(! listObservers.contains(o)) { listObservers.add(o); }
	}

	@Override
	public void removeObserver(Observer o) {
		listObservers.remove(o);
	}

	@Override
	public void notifyObservers(String prev) {
		for(Observer obs : listObservers) {
			obs.update(this, prev);
		}
	}
}
