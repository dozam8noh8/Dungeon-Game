package unsw.dungeon;

/**
 * The player entity
 * @author Robert Clifton-Everest
 *
 */
public class Player extends Entity {

    private Dungeon dungeon;

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
    	boolean moveable = dungeon.canMove(getX(), getY()-1);
        if ((getY() > 0) && (moveable)) {
            y().set(getY() - 1);
        }
        
    }

    public void moveDown() {
    	boolean moveable = dungeon.canMove(getX(), getY()+1);
        if ((getY() < dungeon.getHeight() - 1)&& (moveable))
            y().set(getY() + 1);
    }

    public void moveLeft() {
    	boolean moveable = dungeon.canMove(getX()-1, getY());
        if ((getX() > 0)&& (moveable))
            x().set(getX() - 1);
    }

    public void moveRight() {
    	boolean moveable = dungeon.canMove(getX()+1, getY());
        if ((getX() < dungeon.getWidth() - 1)&& (moveable)){
            x().set(getX() + 1);
        }
    }

}
