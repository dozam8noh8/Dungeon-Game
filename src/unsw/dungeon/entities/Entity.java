package unsw.dungeon.entities;

import javafx.beans.Observable;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * An entity in the dungeon.
 * @authors Robert Clifton-Everest, Owen Silver and Waqif Alam
 *
 */
public abstract class Entity {

    // IntegerProperty is used so that changes to the entities position can be
    // externally observed.
    private IntegerProperty x, y;
    private boolean canMove = false;
    private BooleanProperty alive = new SimpleBooleanProperty(true);

    /**
     * Create an entity positioned in square (x,y)
     * @param x - the x coordinate of the entity
     * @param y - the y coordinate of the entity
     */
    public Entity(int x, int y) {
        this.x = new SimpleIntegerProperty(x);
        this.y = new SimpleIntegerProperty(y);
    }

    /**
     * Returns the x coordinate of the entity
     * @return x
     */
    public IntegerProperty x() {
        return x;
    }

    /**
     * Returns the y coordinate of the entity
     * @return y
     */
    public IntegerProperty y() {
        return y;
    }
    
    /**
     * Returns boolean of whether entity can move or not
     * @return boolean of whether entity can move or not
     */
    public boolean getCanMove() {
    	return this.canMove;
    }
    
    /**
     * Sets if enemy can move or not
     * @param b - of whether enemy can move or not
     */
    public void setCanMove (boolean b) {
    	this.canMove = b;
    }
    
    /**
     * Get y coordinate of entity
     * @return y coordinate of entity
     */
    public int getY() {
        return y().get();
    }
    
    /**
     * Get x coordinate of entity
     * @return x coordinate of entity
     */
    public int getX() {
        return x().get();
    }
    /**
     * If an entity is on the stepped on square, call this method
     * Should be overridden in all classes that do something when walked on.
     * @param p - the player moving on the square
     * @param direction - the direction in which the player is moving.
     */
    public void squareBehav(Player p, String direction) {
    	
    }
    
    /**
     * A second squareBehaviour method for entities such as enemies and boulders.
     * Other entities may have different behaviours when walked on by another 
     * non player entity rather than a player
     * @param e - the entity moving onto the square
     * @param direction - the direction in which the entity is moving.
     */
    public void squareBehav(Entity e, String direction) {
    	
    }
    
    /**
     * Allow boulders or enemies to move into entities by default and
     * change if boulders and enemies cannot move into some entities
     * @return true if entity can move through, false if entity cannot move through
     */
    public boolean entityMoveThrough() {
    	return true;
    }
    
    /**
     * Returns if entity is alive or not
     * @return BooleanProperty of whether entity can move or not
     */
    public BooleanProperty isAlive() {
    	return this.alive;
    }
    
    /**
     * Sets entity as moveable or not moveable
     * @param bool
     */
    public void setAlive(Boolean bool) {
    	this.alive.setValue(bool);
    }
}
