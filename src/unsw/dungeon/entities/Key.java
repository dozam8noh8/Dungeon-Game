package unsw.dungeon.entities;

import unsw.dungeon.Dungeon;

/**
 * A key class to open doors with the same id. A door can only be opened with key of same id.
 * @author Owen Silver and Waqif Alam
 *
 */
public class Key extends Entity {
	private int id;
	private Dungeon dungeon;
	private boolean pickedUp; //so that we can display or not display key without having to remove it from dungeon.
	private boolean justDropped; //so that we dont repickup the key when we drop it down
	
	public Key(Dungeon dungeon, int x, int y, int id) {
		super(x, y);
		this.id = id;
		this.dungeon = dungeon;
		this.pickedUp = false;
		this.justDropped = false;
	}

	/**
	 * Set the X coordinate for key object
	 * @param x1 - x-coordinate to change to
	 */
	public void setX(int x1) {
		x().set(x1);
	}
	
	/**
	 * Set the Y coordinate for key object
	 * @param x1 - Y-coordinate to change to
	 */
	public void setY(int x1) {
		y().set(x1);
	}
	
	/**
	 * Set the id of the key
	 * @param id - Id to change to
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * Returns the id of the key
	 * @return id - ID of the key
	 */
	public int getId() {
		return this.id;
	}
	
	/**
	 * Check if the player is moving into a key. Pick up key if user is
	 * moving into the key. Drop any key if user already has one, and 
	 * replace with the current key
	 */
	@Override //previously we set justdropped to false after using it.
	public void squareBehav(Player p, String direction) {
		if (pickedUp) return; //do nothing because we already have the key
		if (justDropped) return;
		p.setKey(this);
		this.setPickedUp(true);
		this.alive.setValue(false);
	}

	/**
	 * Change pickedUp attribute to pick up or put down key
	 * @param b - boolean to change attribute to
	 */
	public void setPickedUp(boolean b) {
		this.pickedUp = b;
		if (b) {
			System.out.println("Picked up key" + this.getId());
		}
		else {
			System.out.println("Put down key" + this.dungeon.getPlayer().getKeyId()); //change this long chain haha
		}
	}

	/**
	 * Set justDropped attribute to check if key is just dropped
	 * @param b
	 */
	public void setJustDropped(boolean b) {
		this.justDropped = b;
	}
}
