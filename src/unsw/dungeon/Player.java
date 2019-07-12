package unsw.dungeon;

/**
 * The player entity
 * @author Robert Clifton-Everest
 *
 */
public class Player extends Entity implements Movable, Subject {

    private Dungeon dungeon;
    private boolean canMove;
    private Observer enemy;
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
				enemy = (Observer) e;
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
		enemy.update(this);
	}

}
