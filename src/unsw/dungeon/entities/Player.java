package unsw.dungeon.entities;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import unsw.dungeon.Dungeon;
import unsw.dungeon.NoPotionState;
import unsw.dungeon.Observer;
import unsw.dungeon.PotionState;
import unsw.dungeon.PotionStatePlayer;
import unsw.dungeon.PotionStateThread;
import unsw.dungeon.Subject;
import unsw.dungeon.Weapon;

/**
 * The player entity representing the player of the game. Players are used to
 * as a means move through the game and complete level objectives.
 * @author Robert Clifton-Everest, Waqif Alam and Owen Silver
 *
 */
public class Player extends Entity implements Subject {

    private Dungeon dungeon; //Dungeon player is within
    private List<Observer> observers = new ArrayList<Observer>(); //List of observers within dungeon.
  //State representing whether a a player is under the effects of a potion.
    private PotionState potionState = new NoPotionState(); 
    private Weapon weapon = null; //the weapon the player is currently holding
    private List<Bomb> bombs = new ArrayList<Bomb>(); //list of bombs the player is holding
    private List<Treasure> treasures = new ArrayList<Treasure>();  //list of treasures colected
    private StringProperty collectedTreasure = new SimpleStringProperty("0");
    private Key key; //current key being held.
    private StringProperty weaponName = new SimpleStringProperty("None");
	private StringProperty bombCount = new SimpleStringProperty("None");
	private StringProperty potionStateInfo = new SimpleStringProperty("You don't have potion");
	private IntegerProperty lives = new SimpleIntegerProperty(1);
	private IntegerProperty keyId = new SimpleIntegerProperty(-1);
	private int killedEnemies = 0;
	private StringProperty enemyInformation = new SimpleStringProperty("None");

	/**
     * Create a player positioned in square (x,y)
     * @param x - the x coordinate of the square the player will be created on
     * @param y - the y coordinate of the square the player will be created on
     * @param dungeon - the dungeon that the player is contained within.
     */
    public Player(Dungeon dungeon, int x, int y) {
        super(x, y);
        this.dungeon = dungeon;
        this.setCanMove(true);
        
    }
    
    /**
     * Get how many treasure is collected by player
     * @return StringProperty that says how many treasure is collected
     */
    public StringProperty getColTreasureFX () {
    	return this.collectedTreasure;
    }
    
    /**
     * Get how many treasure is collected by player
     * @return int of how many treasure is collected
     */
    public String getColTreasure() {
    	return this.collectedTreasure.getValue();
    }
    
    /**
     * Increment the number of treasure the player holds
     */
    public void incrementTrez() {
    	int newNum = Integer.parseInt(collectedTreasure.getValue())+1;
    	this.collectedTreasure.setValue(Integer.toString(newNum));
    }
    /**
     * After checking if it is a valid move in the dungeon, this will move the player up.
     * The method also notifies any observers of the player that the player has moved.
     */
    public void moveUp() {
    	dungeon.makeMovePlayer(getX(), getY()-1, "up");
        if ((getY() > 0) && (getCanMove())) {
            y().set(getY() - 1);
            addObserver();
            notifyObservers();
        }
        setCanMove(true); //BAD DESIGN?
    }
    
    /**
     * After checking if it is a valid move in the dungeon, this will move the player down.
     * The method also notifies any observers of the player that the player has moved.
     */
    public void moveDown() {
    	dungeon.makeMovePlayer(getX(), getY()+1, "down");
        if ((getY() < dungeon.getHeight() - 1)&& (getCanMove())) {
            y().set(getY() + 1);
            addObserver();
            notifyObservers();
        }
        setCanMove(true);
    }

    /**
     * After checking if it is a valid move in the dungeon, this will move the player left.
     * The method also notifies any observers of the player that the player has moved.
     */
    public void moveLeft() {
    	dungeon.makeMovePlayer(getX()-1, getY(), "left");
        if ((getX() > 0)&& (getCanMove())) {
            x().set(getX() - 1);
            addObserver();
        	notifyObservers();
        }
        setCanMove(true);
    }

    /**
     * After checking if it is a valid move in the dungeon, this will move the player right.
     * The method also notifies any observers of the player that the player has moved.
     */
    public void moveRight() {
    	dungeon.makeMovePlayer(getX()+1, getY(), "right");
        if ((getX() < dungeon.getWidth() - 1)&& (getCanMove())){
            x().set(getX() + 1);
            addObserver();
            notifyObservers();
        }
        setCanMove(true);
    }

    /**
     * Returns the canMove boolean which represents whether a player can move or not.
     * @return boolean - canMove, whether the player is able to move on a particular turn.
     */
	public boolean isCanMove() {
		return getCanMove();
	}


	/**
	 * Registers all observers as observers.
	 */
	@Override
	public void registerObserver(Observer o) {
		if (!observers.contains(o)) {
			observers.add(o);
		}	
	}

	/**
	 * Calls registerObserver method on all observers to add them as observers
	 * to be notified when a player moves.
	 */
	public void addObserver() {
		for (Entity e : dungeon.getEntities()) {
			if ((e instanceof Enemy) || (e instanceof Exit)) {
				registerObserver((Observer)e);
			}

		}
	}
	/**
	 * Removes an observer (enemy) from the notify list.
	 */
	@Override
	public void removeObserver(Observer o) {
		observers.remove(o);
	}

	/**
	 * notifies all observer of the player change
	 */
	@Override
	public void notifyObservers() {
		for (Observer o : observers) {
			o.update(this);
		}
	}
	
	/**
	 * Returns all the observers that are observing the player
	 * @return List<Observer> observers that are observing player
	 */
	public List<Observer> getobservers(){
		return this.observers;
	}

	/**
	 * Return the dungeon copy that the player stores, representing the dungeon
	 * that the player is playing in.
	 * @return Dungeon instance player is playing in.
	 */
	public Dungeon getDungeon() {
		return dungeon;
	}
	
	/**
	 * Changes a player to the potion state, starting a timer that will run for X seconds
	 * after which the player will be returned to normal state.
	 */
	public void changeToPotionState() {
		potionState = potionState.transition();
		setPotionStateInfo("You've got potion for 10 sec");
		potionThread();
	}
	
	/**
	 * Starts a thread for potion that enables potion state on player for 10 secs then converts
	 * to non potion state
	 */
	private void potionThread() {
	    class OneShotTask implements Runnable {
	        int j = 10;
	        OneShotTask(int i) { j = i; }
	        public void run() {
	        	try { Thread.sleep(1000); } catch (Exception e) {}
	        	if (j == 1) {
	        		changeToNoPotionState();
	        	}
	        	Platform.runLater(new Runnable() {
			        @Override public void run() {
			        	if (j == 1) {
			        		setPotionStateInfo("You don't have potion");
			        	} else {
			        		setPotionStateInfo("You have potion for "+(j-1)+" sec");
			        		Thread t = new Thread(new OneShotTask(j-1));
			        		t.start();
			        	}
			        }
	        	});
	        }
	    }
	    Thread t = new Thread(new OneShotTask(10));
	    t.start();
	}
	
	/**
	 * Returns player to original state that is not under effects of potion.
	 */
	public void changeToNoPotionState() {
		potionState = potionState.transition();
	}

	/**
	 * Returns the potion state of the player.
	 * @return - PotionState representing the potion state of the player.
	 */
	public PotionState getPotionState() {
		return potionState;
	}
	
	/**
	 * Sets the weapon the player has access to.
	 * @param w - the weapon wishing to be set for the player.
	 */
	public void setWeapon(Weapon w) {
		this.weapon = w;
	}
	
	/**
	 * If a player has a weapon, this will call the weapons corresponding
	 * attack method.
	 */
	public void attack() {
		if (this.weapon != null) {
		 	weapon.attack(this);
		} 
	}
	
	/**
	 * Adds a bomb to the players inventory of bombs.
	 * @param b - the Bomb being added.
	 */
	public void addBomb(Bomb b) {
		bombs.add(b);
	}
	
	/**
	 * Returns the list of bombs the player currently holds.
	 * @return - List<Bomb> of bombs the player currently holds.
	 */
	public List<Bomb> getBombs() {
		return this.bombs;
	}
	
	/**
	 * If a player has bombs, uses the most recent bomb that the player has picked up.
	 */
	public void useBomb() {
		if (bombs.size() > 0) {
			bombs.get(bombs.size()-1).lightBomb(getX(), getY());
			bombs.remove(bombs.size() - 1);
			this.setBombCount(getBombs().size()+ " Bombs");
		}
	}


	/**
	 * Returns a list of all treasures a player has collected.
	 * @return - List of all treasure a player has collected.
	 */
	public List<Treasure> getTreasures() {
		return treasures;
	}
	
	/**
	 * Adds a treasure to the list that a player has collected.
	 * @param treasure - the treasure wishing to be added.
	 */
	public void addTreasure(Treasure treasure) {
		this.treasures.add(treasure);
		this.incrementTrez();
	}
	
	/**
	 * Called when a player steps on a key, sets the key of the player to the key passed in.
	 * If a player already has a key, the old key will be dropped on the square the new key
	 * was picked up. Players must attempt to move once before they can pick back the old
	 * key.
	 * @param k - the key wishing to be set as the players current key.
	 */
	public void setKey(Key k) {
		if (k == null) { //we could make a property/state called nokey if we wanted to.
			this.key = null;
		}
		if (key != null) { //puts key down where other key was
			key.setX(k.getX());
			key.setY(k.getY());
			key.setPickedUp(false);
			key.setJustDropped(true); //set just dropped attribute so key isnt immediately
								      //picked back up
		}
		key = k;
		if (key == null) {
			keyId.setValue(-1);
		} else {
			keyId.setValue(key.getId());
		}
	}
	
	/**
	 * Returns the current key the player is holding.
	 * @return Key that the player is holding.
	 */
	public Key getKey() {
		return this.key;
	}
	/**
	 * Returns the id of the key that the player is holding. If 
	 * the player is not holding a key, will return -1.
	 * @return
	 */
	public int getKeyId() {
		if (key == null) return -1;
		return key.getId();
	}

	/**
	 * Returns the instance of the weapon the player is currently wielding.
	 * @return - the instance of the weapon the player is using.
	 */
	public Weapon getWeapon() {
		return this.weapon;
	}

	/**
	 * sets player alive to false indicating player is dead.
	 */
	public void killPlayer() {
		System.out.println("killing player");
		if (getLives() == 1) {
			setAlive(false);
		} else {
			decrementLives();
		}
		
	}
	
	/**
	 * If an enemy is moving to a player, the game will end
	 * @return Boolean of if entity can move through
	 */
	@Override
	public boolean entityMoveThrough() {
		System.out.println("Enemy caught a player");
		if (this.potionState instanceof PotionStatePlayer) {
			System.out.println("Invincible");
			return false;
		}
		this.killPlayer();
		return false;
	}

	/**
	 * Get the weapon name of the player
	 * @return StringProperty of the weapon name
	 */
	public StringProperty getWeaponName() {
		return this.weaponName;
	}
	
	/**
	 * Set Weapon name being held by the player
	 * @param name - name of the weapon
	 */
	public void setWeaponName(String name) {
		this.weaponName.setValue(name);
	}

	/**
	 * Get the count of how many bombs player holds
	 * @return
	 */
	public StringProperty getBombCount() {
		return this.bombCount;
	}
	
	/**
	 * Set the bomb count held by the player
	 * @param count - how many bombs held by player
	 */
	public void setBombCount(String count) {
		this.bombCount.setValue(count);
	}

	/**
	 * Get the potionstateinfo of the player
	 * @return StringProperty of the potion state of the player
	 */
	public StringProperty getPotionStateInfo() {
		return this.potionStateInfo;
	}
	
	/**
	 * Set the potionstateinfo of the player
	 * @param info - potion state info of the player
	 */
	public void setPotionStateInfo(String info) {
		this.potionStateInfo.setValue(info);
	}
	
	/**
	 * Decrement lives for player
	 */
	public void decrementLives() {
		this.lives.setValue(lives.getValue()-1);
	}
	
	/**
	 * Increment lives for player
	 */
	public void incrementLives() {
		this.lives.setValue(lives.getValue()+1);
	}
	
	public int getLives() {
		return this.lives.getValue();
	}
	
	public IntegerProperty getLivesProperty() {
		return this.lives;
	}

	public StringProperty getEnemyInformation() {
		enemyInformation.setValue(killedEnemies+"/"+dungeon.getDungeonEnemies());
		return enemyInformation;
	}
	
	public void incrementEnemies() {
		this.killedEnemies = killedEnemies+1;
		dungeon.getEnemyInformation();
	}
	
	public IntegerProperty getKeyIdProperty() {
		return this.keyId;
	}
}
