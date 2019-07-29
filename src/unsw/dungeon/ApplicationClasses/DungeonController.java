package unsw.dungeon.ApplicationClasses;

import java.util.ArrayList;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import unsw.dungeon.Dungeon;
import unsw.dungeon.entities.Entity;
import unsw.dungeon.entities.Player;

/**
 * A JavaFX controller for the dungeon.
 * @author Robert Clifton-Everest
 *
 */
public class DungeonController {

    @FXML
    private GridPane squares;

    private List<ImageView> initialEntities;

    private Player player;

    private Dungeon dungeon;

	private DungeonMenuScreen menuScreen;

    public DungeonController(Dungeon dungeon, List<ImageView> initialEntities) {
        this.dungeon = dungeon;
        this.player = dungeon.getPlayer();
        this.initialEntities = new ArrayList<>(initialEntities);
    }

    @FXML
    public void initialize() {
        Image ground = new Image("/dirt_0_new.png");

        // Add the ground first so it is below all other entities
        for (int x = 0; x < dungeon.getWidth(); x++) {
            for (int y = 0; y < dungeon.getHeight(); y++) {
                squares.add(new ImageView(ground), x, y);
            }
        }

        for (ImageView entity : initialEntities)
            squares.getChildren().add(entity);
    }
    
    // To implement
    public void removeEntities() {
    	for (Entity e : dungeon.getEntities()) {
    		if (!e.isAlive().getValue()) {
    			System.out.println(e+"------ is not alive");
    		}
    	}
    }

    @FXML
    public void handleKeyPress(KeyEvent event) {
        switch (event.getCode()) {
        case UP:
            player.moveUp();
            break;
        case DOWN:
            player.moveDown();
            break;
        case LEFT:
            player.moveLeft();
            break;
        case RIGHT:
            player.moveRight();
            break;
        case L:
        	player.useBomb();
        	break;
        case SPACE:
        	player.attack();
        	break;
        case ESCAPE:
        	menuScreen.start();
        default:
            break;
        }
        if (dungeon.isComplete()) {
        	menuScreen.start();
        }
        
        if (!dungeon.getPlayer().isAlive().getValue()) {
        	menuScreen.start();
        }
        
        removeEntities();
    }
    
    public void setMenuScreen(DungeonMenuScreen menuScreen) {
		this.menuScreen = menuScreen;
	}

}

