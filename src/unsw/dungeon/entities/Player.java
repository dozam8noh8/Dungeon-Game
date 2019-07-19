package unsw.dungeon.entities;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import unsw.dungeon.Dungeon;
import unsw.dungeon.NoPotionState;
import unsw.dungeon.Observer;
import unsw.dungeon.PotionState;
import unsw.dungeon.PotionStateThread;
import unsw.dungeon.Subject;
import unsw.dungeon.Weapon;

/**
 * The player entity
 * @author Robert Clifton-Everest
 *
 */
public class Player extends Entity implements Subject {

    private Dungeon dungeon;
    private boolean canMove;
    private List<Observer> enemies = new ArrayList<Observer>();
    private PotionState potionState = new NoPotionState();
    private Weapon weapon = null;
    private List<Bomb> bombs = new ArrayList<Bomb>();
    private List<Treasure> treasures = new ArrayList<Treasure>();
    private Key key;

	/**
     * Create a player positioned in square (x,y)
     * @param x
     * @param y
     */
    public Player(Dungeon dungeon, int x, int y) {
        super(x, y);
        this.dungeon = dungeon;
        this.canMove = true;
    }

    public void moveUp() {
    	dungeon.makeMovePlayer(getX(), getY()-1, "up");
        if ((getY() > 0) && (canMove)) {
            y().set(getY() - 1);
            addObserver();
            notifyObservers();
        }
        canMove = true; //BAD DESIGN?
    }

    public void moveDown() {
    	dungeon.makeMovePlayer(getX(), getY()+1, "down");
        if ((getY() < dungeon.getHeight() - 1)&& (canMove)) {
            y().set(getY() + 1);
            addObserver();
            notifyObservers();
        }
        canMove = true; //BAD DESIGN?
    }

    public void moveLeft() {
    	dungeon.makeMovePlayer(getX()-1, getY(), "left");
        if ((getX() > 0)&& (canMove)) {
            x().set(getX() - 1);
            addObserver();
        	notifyObservers();
        }
        canMove = true; //BAD DESIGN?
    }

    public void moveRight() {
    	dungeon.makeMovePlayer(getX()+1, getY(), "right");
        if ((getX() < dungeon.getWidth() - 1)&& (canMove)){ //should separate first if for before makeMovePlayer call
            x().set(getX() + 1);
            addObserver();
            notifyObservers();
        }
        canMove = true; //BAD DESIGN?
    }

	public boolean isCanMove() {
		return canMove;
	}

	public void setCanMove(boolean canMove) {
		this.canMove = canMove;
	}

	@Override
	public void registerObserver(Observer o) {
		if (!enemies.contains(o)) {
			enemies.add(o);
		}	
	}

	public void addObserver() {
		for (Entity e : dungeon.getEntities()) {
			if (e instanceof Enemy) {
				registerObserver((Observer)e);
			}
		}
	}
	@Override
	public void removeObserver(Observer o) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notifyObservers() {
		// TODO Auto-generated method stub
		for (Observer o : enemies) {
			o.update(this);
		}
	}

	public Dungeon getDungeon() {
		// TODO Auto-generated method stub
		return dungeon;
	}
	
	public void changeToPotionState() {
		potionState = potionState.changeToPotionState();
		PotionStateThread potionThread = new PotionStateThread(this);
		potionThread.start();
	}
	
	public void changeToNoPotionState() {
		potionState = potionState.changeToNoPotionState();
	}

	public PotionState getPotionState() {
		// TODO Auto-generated method stub
		return potionState;
	}
	
	public void setWeapon(Weapon w) {
		this.weapon = w;
	}
	public void attack() {
		if (this.weapon != null) {
		 	weapon.attack(this);
		} 
	}
	
	
	public void addBomb(Bomb b) {
		bombs.add(b);
	}
	
	public List<Bomb> getBombs() {
		return this.bombs;
	}
	
	public void useBomb() {
		if (bombs.size() > 0) {
			System.out.println("Attacking");
			bombs.get(bombs.size()-1).lightBomb();
			bombs.remove(bombs.size() - 1);
		}
	}



	public List<Treasure> getTreasures() {
		return treasures;
	}

	public void setTreasures(List<Treasure> treasures) {
		this.treasures = treasures;
	}
	
	public void addTreasure(Treasure treasure) {
		this.treasures.add(treasure);
	}
	
	public void setKey(Key k) {
		if (k == null) { //we could make a property/state called nokey if we wanted to.
			this.key = null;
		}
		if (key != null) { //puts key down where other key was?
			System.out.println("Player stepped on key, with id " + k.getId());
			key.setX(k.getX());
			key.setY(k.getY());
			key.setPickedUp(false);
			key.setJustDropped(true);
		}
		key = k;
	}
	
	// To implement
	public Key getKey() {
		// TODO Auto-generated method stub
		return this.key;
	}
	public int getKeyId() {
		if (key == null) return -1;
		return key.getId();
	}

	public Weapon getWeapon() {
		// TODO Auto-generated method stub
		return this.weapon;
	}
}
