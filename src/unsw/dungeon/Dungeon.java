/**
 *
 */
package unsw.dungeon;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import unsw.dungeon.entities.Boulder;
import unsw.dungeon.entities.Entity;
import unsw.dungeon.entities.Key;
import unsw.dungeon.entities.PPlate;
import unsw.dungeon.entities.Player;
import unsw.dungeon.entities.Wall;
import unsw.dungeon.objectives.BoulderObjective;
import unsw.dungeon.objectives.EnemyObjective;
import unsw.dungeon.objectives.ExitObjective;
import unsw.dungeon.objectives.Objective;
import unsw.dungeon.objectives.TreasureObjective;

/**
 * A dungeon in the interactive dungeon player.
 *
 * A dungeon can contain many entities, each occupy a square. More than one
 * entity can occupy the same square.
 *
 * @author Robert Clifton-Everest
 *
 */
public class Dungeon implements Observer{

    private int width, height;
    private List<Entity> entities;
    private Player player;
    private List<PPlate> plates = new ArrayList<PPlate>();
    private Objective objective;
    private boolean complete = false;
    //private ArrayList<Objective> objectives = new ArrayList<Objective>();
    //to make things quicker, it may be worth having a list of switches, list of treasure... etc, so we can check objectives quicker.s

    public Dungeon(int width, int height) {
        this.width = width;
        this.height = height;
        this.entities = new ArrayList<>();
        this.player = null;
    }
    public void setFinalObjective (Objective o) {
    	this.objective = o;
    }
    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void addEntity(Entity entity) {
        entities.add(entity);
    }
    
    public void makeMovePlayer (int x, int y, String direction) {
    	ArrayList<Entity> entOnSq = getEntOnSq( x, y);
    	for (Entity e: entOnSq) {
    		e.squareBehav(this.player, direction); //might need to take in something different later.
    	}
    	//this should call squarebehaviour on the next 
    }
    public void makeMove( int x, int y, String direction) {
    	ArrayList<Entity> entOnSq = getEntOnSq( x, y);
    	for (Entity e: entOnSq) {
    		e.squareBehav(this.player, direction); //might need to take in something different later.
    	}
    }
    //Do we pass in the thing we are trying to move and then (i.e we call make move with *this* as the player or *this* as the boulder
    //makeMove would still either need multiple functions or to check instance of here.
    public boolean makeMoveBoulderOrEnemy (int x, int y) {
    	ArrayList<Entity> entOnSq = getEntOnSq( x, y);
    	for (Entity e: entOnSq) {
    		if (e == null) continue;
    		if (e instanceof Wall) {
    			return false;
    			
    		}
    		if (e instanceof Boulder) {
    			return false;
    		}
    		if ( e instanceof Player) { //we should try make another interface
    			System.out.println("Enemy caught a player");
    			System.exit(1);
    		}
    	}
    	return true;
    }
    public ArrayList<Entity> getEntOnSq (int x, int y) {
    	ArrayList<Entity> entOnSq = new ArrayList<Entity>();
    	for (Entity e: this.entities) {
    		if (e == null) {
    			continue;
    		}
    		//TEMPORARY REMOVE/move THIS
    		if (e instanceof Key) {
    			//System.out.println("reset");
    			((Key) e).setJustDropped(false);
    		}
    		if ((e.getX() == x) && (e.getY() == y)) { //only if wall.
    			entOnSq.add(e);
    		}
    	}
		return entOnSq;
    }
    public boolean checkDungeonObjectives() {
    	for (Entity e: this.entities) {
    		if (e == null) {
    			continue;
    		}
    		//if switch, check if pressed
    		//if treasure, check if collected
    		//if enemy, check if killed
    	}
    	return true; //justfor testing will always return true.
    }
    
    public List<Entity> getEntities(){
		return this.entities;
    	
    }

	public List<PPlate> getPlates() {
		return plates;
	}

	public void setPlates() {
		for (Entity e : entities) {
			if (e instanceof PPlate) {
				if (!plates.contains(e)) {
					plates.add((PPlate) e);
				}
			}
		}
	}

	@Override
	public void update(Subject o) {
		int count = 0;
		setPlates();
		// TODO Auto-generated method stub
		for (PPlate p : getPlates()) {
			ArrayList<Entity> entOnSq = getEntOnSq( p.getX(), p.getY());
	    	for (Entity e: entOnSq) {
	    		if (e instanceof Boulder) {
	    			count = count +1;
	    			//System.out.println(count + " plates are pressed");
	    		}
	    	}
		}
		if (count == getPlates().size()) {
			completeBoulderObjective(this.getObjective());
		} else {
			removeBoulderObjective();
		}
	}

	public void removeEntity(Entity e1) {
		this.entities.remove(e1);
	}
	
	
	public void completeExitObjective(Objective obj) {
		if (obj == null) return;
		if (obj.getObjectives() == null) {
			if (obj instanceof ExitObjective) {
				System.out.println("Instance of Exit objective");
				obj.complete(obj);
			}
			return;
		}
		for (Objective o : obj.getObjectives()) {
			System.out.println(o);
			completeExitObjective(o);
		}
		checkObjectives();
	
	}

	private void checkObjectives() {
		// TODO Auto-generated method stub

		System.out.println("Checking objectives");
		Boolean finish = objective.isComplete();
		if (finish){
			System.out.println(" objective complete");
			this.complete=true;
		}

	}
	
	public boolean getComplete() {
		return this.complete;
	}
	
	public void completeBoulderObjective(Objective obj) {
		if (obj == null) return;
		if (obj.getObjectives() == null) {
			if (obj instanceof BoulderObjective) {
				System.out.println("Instance of Boulder obj");
				obj.complete(obj);
			}
			return;
		}
		for (Objective o : obj.getObjectives()) {
			System.out.println(o);
			completeBoulderObjective(o);
		}
		checkObjectives();
	}
	
	public void completeTreasureObjective(Objective obj) {
		if (obj == null) return;
		if (obj.getObjectives() == null) {
			if (obj instanceof TreasureObjective) {
				System.out.println("Instance of treasure");
				obj.complete(obj);
			}
			return;
		}
		for (Objective o : obj.getObjectives()) {
			System.out.println(o);
			completeTreasureObjective(o);
		}
		checkObjectives();
	}
	public void completeEnemyObjective(Objective obj) {
		if (obj == null) return;
		if (obj.getObjectives() == null) {
			if (obj instanceof EnemyObjective) {
				System.out.println("Instance of enemy");
				obj.complete(obj);
			}
			return;
		}
		for (Objective o : obj.getObjectives()) {
			System.out.println(o);
			completeEnemyObjective(o);
		}
		checkObjectives();

	}
	
	public void removeBoulderObjective() {
		for (Objective o : objective.getObjectives()) {
			if (o instanceof BoulderObjective) {
				o.incomplete(o);
			}
		}
	}
	public Objective getObjective() {
		return objective;
	}
	public void addObjective(Objective o) {
		objective.addChild(o);
	}
}
