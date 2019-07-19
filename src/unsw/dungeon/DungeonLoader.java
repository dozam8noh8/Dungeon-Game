package unsw.dungeon;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import javafx.scene.image.Image;

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
        
        Objective test = new StrategyObjective(new ArrayList<Objective>() , new AndObjectives());
        handleObjectiveCases(test, dungeon, jsonGoals);
        dungeon.setFinalObjective(test);

        for (int i = 0; i < jsonEntities.length(); i++) {
            loadEntity(dungeon, jsonEntities.getJSONObject(i));
        }
        return dungeon;
    }
    private Objective createOrObjective (Dungeon dungeon, JSONArray jsonArr) {
    	Objective o = new StrategyObjective(new ArrayList<Objective>(), new OrObjectives() );
    	for (int i = 0; i < jsonArr.length(); i++) {
    		JSONObject jobj = jsonArr.getJSONObject(i);
    		handleObjectiveCases(o, dungeon, jobj);
    	}
    	return o;
    }
    private Objective createAndObjective (Dungeon dungeon, JSONArray jsonArr) {
    	Objective o = new StrategyObjective(new ArrayList<Objective>(), new AndObjectives());
    	for (int i = 0; i < jsonArr.length(); i++) {
    		JSONObject jobj = jsonArr.getJSONObject(i);
    		handleObjectiveCases(o, dungeon, jobj);
    	}
    	return o;
    }
    private void handleObjectiveCases(Objective currObj, Dungeon dungeon, JSONObject json) { //adds to current objective.
    	//System.out.println("In handle");
    	String goal = json.getString("goal");
    	switch(goal) { //we no longer default instantiate an objective in the dungeon so we need to add one before we can add children.
    	case "AND":
    		//Do some composite stuff
    		currObj.addChild(createAndObjective(dungeon, json.getJSONArray("subgoals")));
    		System.out.println("Added and");
    		break;
    	case "OR":
    		currObj.addChild(createOrObjective(dungeon, json.getJSONArray("subgoals")));
    		//Do some composite stuff
    		System.out.println("Added or");
    		break;
    	case "enemies":
    		currObj.addChild(new EnemyObjective());
    		System.out.println("Added Enemy");
    		break;
    	case "treasure":
    		currObj.addChild(new TreasureObjective());
    		System.out.println("Added Treasure");
    		break;
    	case "exit":
    		currObj.addChild(new ExitObjective());
    		System.out.println("Added Exit");
    		break;
    	case "boulders":
    		currObj.addChild(new BoulderObjective());
    		System.out.println("Added Boulder");
    		break;
    	}
    }
    /*private void setObjectives(Dungeon dungeon, JSONObject json) {
    	System.out.println(json);
    	String goal = json.getString("goal");
    	switch(goal) { //we no longer default instantiate an objective in the dungeon so we need to add one before we can add children.
    	case "AND":
    		//Do some composite stuff
    		createAndObjective(dungeon, json.getJSONArray("subgoals"));
    		break;
    	case "OR":
    		createOrObjective(dungeon, json.getJSONArray("subgoals"));
    		//Do some composite stuff
    		break;
    	case "enemies":
    		dungeon.getObjective().addChild(new EnemyObjective());
    		break;
    	case "treasure":
    		dungeon.getObjective().addChild(new TreasureObjective());
    		break;
    	case "exit":
    		dungeon.getObjective().addChild(new ExitObjective());
    		break;
    	case "boulders":
    		dungeon.getObjective().addChild(new BoulderObjective());
    		break;
    	}
    }*/
    private void loadEntity(Dungeon dungeon, JSONObject json) {
        String type = json.getString("type");
        int x = json.getInt("x");
        int y = json.getInt("y");

        Entity entity = null;
        switch (type) {
        case "player":
            Player player = new Player(dungeon, x, y);
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
        }
       
        dungeon.addEntity(entity);
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

	public abstract void onLoad(Switch pressure_plate);

	public void onLoad() {
		// TODO Auto-generated method stub
		
	} 


    // TODO Create additional abstract methods for the other entities

}
