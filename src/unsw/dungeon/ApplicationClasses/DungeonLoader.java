package unsw.dungeon.ApplicationClasses;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import javafx.scene.image.Image;
import unsw.dungeon.Dungeon;
import unsw.dungeon.entities.Bomb;
import unsw.dungeon.entities.BossEnemy;
import unsw.dungeon.entities.Boulder;
import unsw.dungeon.entities.Door;
import unsw.dungeon.entities.Enemy;
import unsw.dungeon.entities.Entity;
import unsw.dungeon.entities.Exit;
import unsw.dungeon.entities.Key;
import unsw.dungeon.entities.Life;
import unsw.dungeon.entities.PPlate;
import unsw.dungeon.entities.Player;
import unsw.dungeon.entities.Potion;
import unsw.dungeon.entities.Sword;
import unsw.dungeon.entities.Treasure;
import unsw.dungeon.entities.Wall;
import unsw.dungeon.objectives.AndObjectives;
import unsw.dungeon.objectives.BoulderObjective;
import unsw.dungeon.objectives.EnemyObjective;
import unsw.dungeon.objectives.ExitObjective;
import unsw.dungeon.objectives.Objective;
import unsw.dungeon.objectives.OrObjectives;
import unsw.dungeon.objectives.StrategyObjective;
import unsw.dungeon.objectives.TreasureObjective;

/**
 * Loads a dungeon from a .json file.
 *
 * By extending this class, a subclass can hook into entity creation. This is
 * useful for creating UI elements with corresponding entities.
 *
 * @author Robert Clifton-Everest
 *
 */
public abstract class DungeonLoader {

    private JSONObject json;
    private String objString = "";
    

    public DungeonLoader(String filename) throws FileNotFoundException {
        json = new JSONObject(new JSONTokener(new FileReader("dungeons/" + filename)));
    }

    /**
     * Parses the JSON to create a dungeon.
     * @return
     */
    public Dungeon load() {
        int width = json.getInt("width");
        int height = json.getInt("height");

        Dungeon dungeon = new Dungeon(width, height);

        JSONArray jsonEntities = json.getJSONArray("entities");	
        JSONObject jsonGoals = json.getJSONObject("goal-condition");
        if (jsonGoals.isEmpty()) {
        	System.out.println("No goals found");
        	return null;
        }
        
        Objective test = new StrategyObjective(new ArrayList<Objective>() , new AndObjectives());
        handleObjectiveCases(test, dungeon, jsonGoals);
        dungeon.setFinalObjective(test);
        

        for (int i = 0; i < jsonEntities.length(); i++) {
            loadEntity(dungeon, jsonEntities.getJSONObject(i));
        }
        if (dungeon.getPlayer() == null || dungeon.getObjective()==null) {
        	System.out.println("No character found");
        	return null;
        }
       dungeon.initialiseNumTreasures();
       System.out.println(objString);
        return dungeon;
    }
    
    /**
     * Creates an OrObjective if the JSON has an OR objective to complete
     * @param dungeon
     * @param jsonArr
     * @return
     */
    private Objective createOrObjective (Dungeon dungeon, JSONArray jsonArr) {
    	objString += " \n( ";
    	Objective o = new StrategyObjective(new ArrayList<Objective>(), new OrObjectives() );
    	for (int i = 0; i < jsonArr.length(); i++) {
    		JSONObject jobj = jsonArr.getJSONObject(i);
    		handleObjectiveCases(o, dungeon, jobj);
    		if (i != jsonArr.length() -1) {
    			objString += "\n    OR \n";
    		}
    	}
    	objString += " )";
    	return o;
    }
    
    /**
     * Creates an AndObjective if the JSON has an AND objective to complete
     * @param dungeon
     * @param jsonArr
     * @return
     */
    private Objective createAndObjective (Dungeon dungeon, JSONArray jsonArr) {
    	objString += "\n ( ";
    	Objective o = new StrategyObjective(new ArrayList<Objective>(), new AndObjectives());
    	for (int i = 0; i < jsonArr.length(); i++) {
    		JSONObject jobj = jsonArr.getJSONObject(i);
    		handleObjectiveCases(o, dungeon, jobj);
    		if (i != jsonArr.length() -1) {
    			objString += "\n    AND ";
    		}
    	}
    	objString += ")";
    	return o;
    }
    
    /**
     * Method to handle cases for Objectives
     * @param currObj
     * @param dungeon
     * @param json
     */
    private void handleObjectiveCases(Objective currObj, Dungeon dungeon, JSONObject json) { //adds to current objective.
    	//System.out.println("In handle");
    	String goal = json.getString("goal");
    	switch(goal) { //we no longer default instantiate an objective in the dungeon so we need to add one before we can add children.
    	case "AND":
    		//Do some composite stuff
    		//objString += "\nAND";
    		currObj.addChild(createAndObjective(dungeon, json.getJSONArray("subgoals")));
    		System.out.println("Added and");
    		break;
    	case "OR":
    		//objString+= "\nOR";
    		currObj.addChild(createOrObjective(dungeon, json.getJSONArray("subgoals")));
    		//Do some composite stuff
    		System.out.println("Added or");
    		break;
    	case "enemies":
    		objString += " Kill All Enemies";
    		currObj.addChild(new EnemyObjective());
    		System.out.println("Added Enemy");
    		break;
    	case "treasure":
    		objString += " Collect all treasure";
    		currObj.addChild(new TreasureObjective());
    		System.out.println("Added Treasure");
    		break;
    	case "exit":
    		objString +=" Exit the dungeon";
    		currObj.addChild(new ExitObjective());
    		System.out.println("Added Exit");
    		break;
    	case "boulders":
    		objString += " Place all boulders on switches";
    		currObj.addChild(new BoulderObjective());
    		System.out.println("Added Boulder");
    		break;
    	}
    }
    /**
     * Load entities from JSON to dungeon object
     * @param dungeon - Game dungeon
     * @param json - JSON object with entities
     */
    private void loadEntity(Dungeon dungeon, JSONObject json) {
        String type = json.getString("type");
        int x = json.getInt("x");
        int y = json.getInt("y");
        boolean onPlate = false;

        Entity entity = null;
        ArrayList<Entity> entities = dungeon.getEntOnSq(x,y);
        for (Entity e : entities) {
        	if (!(e instanceof PPlate)) {
        		System.out.println("Cannot put entity on another entity");
        		return;
        	} else {
        		onPlate=true;
        	}
        }
        
        if (onPlate) {
        	if (type=="wall"||type=="exit"||type=="door") {
        		return;
        	}
        }
        
        switch (type) {
        case "player":
            Player player = new Player(dungeon, x, y);
            if (dungeon.getEntOnSq(x,y).size()!=0) {
            	System.out.println("Cannot have players on other entities");
            	return;
            }
            dungeon.setPlayer(player);
            onLoad(player);
            entity = player;
            break;
        case "wall":
            Wall wall = new Wall(x, y);
            onLoad(wall);
            entity = wall;
            break;
        // TODO Handle other possible entities
        case "exit":
        	Exit exit = new Exit(dungeon, x, y);
        	onLoad(exit);
        	entity = exit;
        	break;
        case "enemy":
        	Enemy enemy = new Enemy(dungeon, x, y);
        	onLoad(enemy);
        	entity = enemy;
        	break;
        case "door":
        	int id = json.getInt("id");
        	Door door = new Door(x, y, id);
        	onLoad(door);
        	entity = door;
        	break;
        case "key":
        	id = json.getInt("id");
        	Key key = new Key(dungeon, x, y, id);
        	onLoad(key);
        	entity = key;
        	break;
        case "bomb":
        	Bomb bomb = new Bomb(x,y, dungeon);
        	onLoad(bomb);
        	entity = bomb;
        	break;
        case "boulder":
        	Boulder boulder = new Boulder(dungeon, x,y);
        	onLoad(boulder);
        	entity = boulder;
        	break;
        case "switch":
        	PPlate pPlate = new PPlate(x,y);
        	onLoad(pPlate);
        	entity = pPlate;
        	break;
        case "treasure":
        	Treasure treasure = new Treasure (x,y);
        	onLoad(treasure);
        	entity = treasure;
        	break;
        case "invincibility":
        	Potion potion = new Potion(x,y);
        	onLoad(potion);
        	entity = potion;
        	break;
        case "sword":
        	Sword sword = new Sword(x,y);
        	onLoad(sword);
        	entity = sword;
        	break;
        case "life":
        	Life life = new Life(x,y);
        	onLoad(life);
        	entity = life;
        	break;
        case "boss":
        	BossEnemy boss = new BossEnemy(dungeon,x,y);
        	onLoad(boss);
        	entity = boss;
        	break;
        }
       
        dungeon.addEntity(entity);
    }
    public String getObjString() {
    	
    	return this.objString;
    }

    public abstract void onLoad(Entity player);

    public abstract void onLoad(Wall wall);
    
    public abstract void onLoad(Exit exit);
    
    public abstract void onLoad(Enemy enemy);
    
    public abstract void onLoad(Treasure treasure);
    
    public abstract void onLoad(Bomb bomb);
    
    public abstract void onLoad(Boulder boulder);
    
    public abstract void onLoad(PPlate p);
    
    public abstract void onLoad(Key key);
    
    public abstract void onLoad(Door door);
    
    public abstract void onLoad(Potion potion);
    
    public abstract void onLoad(Sword sword);
    
    public abstract void onLoad(Life life);

	public abstract void onLoad(BossEnemy boss);

}
