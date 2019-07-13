/**
 *
 */
package unsw.dungeon;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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
    private Objective objective = new StrategyObjective(new ArrayList<Objective>());
    //private ArrayList<Objective> objectives = new ArrayList<Objective>();
    //to make things quicker, it may be worth having a list of switches, list of treasure... etc, so we can check objectives quicker.s

    public Dungeon(int width, int height) {
        this.width = width;
        this.height = height;
        this.entities = new ArrayList<>();
        this.player = null;
        addObjectives();
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
    	
    }
    public boolean makeMoveBoulder (int x, int y, Boulder b) {
    	ArrayList<Entity> entOnSq = getEntOnSq( x, y);
    	for (Entity e: entOnSq) {
    		if (e == null) continue;
    		if (e instanceof Wall) {
    			return false;
    			
    		}
    		if (e instanceof Boulder) {
    			return false;
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
			addBoulderObjective();
		} else {
			removeBoulderObjective();
		}
	}

	public void removeEntity(Entity e1) {
		// TODO Auto-generated method stub
		Iterator<Entity> itr = entities.iterator();            
		while(itr.hasNext()){
		    Entity e = itr.next();
		    if(e.equals(e1)){
		        itr.remove();
		        e = null;
		    }
		}
	}
	
	
	public void addExitObjective() {
		for (Objective o : objective.getObjectives()) {
			if (o instanceof ExitObjective) {
				o.complete(o);
			}
		}
		checkObjectives();
	}

	private void checkObjectives() {
		// TODO Auto-generated method stub
		Boolean finish = objective.isComplete();
		System.exit(1);
	}
	
	public void addBoulderObjective() {
		for (Objective o : objective.getObjectives()) {
			if (o instanceof BoulderObjective) {
				o.complete(o);
			}
		}
		checkObjectives();
	}
	
	public void addTreasureObjective() {
		for (Objective o : objective.getObjectives()) {
			if (o instanceof TreasureObjective) {
				o.complete(o);
			}
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
	
	public void addObjectives() {
		objective.addChild(new ExitObjective());
		objective.addChild(new BoulderObjective());
		objective.addChild(new TreasureObjective());
	}
}
