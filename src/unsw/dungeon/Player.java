package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;

/**
 * The player entity
 * @author Robert Clifton-Everest
 *
 */
public class Player extends Entity implements Movable, Subject {

    private Dungeon dungeon;
    private boolean canMove;
    private List<Observer> enemy = new ArrayList<Observer>();
    private PotionState potionState = new NoPotionState();
    private SwordState swordState = new NoSwordState();
    private BombState bombState = new NoBombState();
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
		// TODO Auto-generated method stub
	}

	public void addObserver() {
		for (Entity e : dungeon.getEntities()) {
			if (e instanceof Enemy) {
				enemy.add((Observer) e);
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
		for (Observer o : enemy) {
			o.update(this);
		}
	}

	public Dungeon getDungeon() {
		// TODO Auto-generated method stub
		return dungeon;
	}
	
	public void changeToPotionState() {
		potionState = potionState.changeToPotionState();
	}
	
	public void changeToNoPotionState() {
		potionState = potionState.changeToNoPotionState();
	}

	public PotionState getPotionState() {
		// TODO Auto-generated method stub
		return potionState;
	}
	
	public void changeToSwordState() {
		swordState = swordState.changeToSwordState();
	}
	
	public void changeToNoSwordState() {
		swordState = swordState.changeToNoSwordState();
	}

	public SwordState getSwordState() {
		// TODO Auto-generated method stub
		return swordState;
	}
	
	public void changeToBombState() {
		bombState = bombState.changeToBombState();
	}
	
	public void changeToNoBombState() {
		bombState = bombState.changeToNoBombState();
	}

	public BombState getBombState() {
		// TODO Auto-generated method stub
		return bombState;
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
		if (key != null) {
			key.setX(k.getX());
			key.setY(k.getY());
		}
		key = k;
	}
	
	// To implement
	public int getKey() {
		// TODO Auto-generated method stub
		return key.getId();
	}
}
