package unsw.dungeon;

import java.io.FileNotFoundException;
import java.io.FileReader;

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

        for (int i = 0; i < jsonEntities.length(); i++) {
            loadEntity(dungeon, jsonEntities.getJSONObject(i));
        }
        return dungeon;
    }

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
        	Door door = new Door(x, y);
        	onLoad(door);
        	entity = door;
        	break;
        case "key":
        	Key key = new Key(x, y);
        	onLoad(key);
        	entity = key;
        	break;
        case "bomb":
        	Bomb bomb = new Bomb(x,y);
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
