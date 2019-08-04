package unsw.dungeon.ApplicationClasses;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import javafx.beans.binding.Bindings;
import javafx.beans.property.Property;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.util.converter.NumberStringConverter;
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
    
    @FXML
    private Label swordLabel;
    
    @FXML
    private Label swordLabel2;
    
    @FXML
    private Label swordLabel3;
    
    @FXML
    private Label totalTrez;
    
    @FXML
    private Label collectedTrez;
    
    @FXML
    private Label collectedSwitch;
    
    @FXML
    private Label totalLives;

    @FXML
    private Label collectedEnemy;
    
    @FXML
    private ImageView keyBlue;

    @FXML
    private ImageView keyGreen;

    @FXML
    private ImageView keyRed;
    
    @FXML
    private Pane InventoryPane;
    
    @FXML
    private Pane objectivesPane;

    @FXML
    private Label jsonObjectivesLabel;


    private List<ImageView> initialEntities;

    private Player player;

    private Dungeon dungeon;

	private DungeonMenuScreen menuScreen;
	private FinishedLevelScreen finishedLevelScreen;
	private FailedLevelScreen failedLevelScreen;
	private InstructionScreen instructionScreen;
	private DungeonGameScreen gameScreen;
	private DungeonStartScreen startScreen;

    public DungeonController(Dungeon dungeon, List<ImageView> initialEntities) {
        this.dungeon = dungeon;
        this.player = dungeon.getPlayer();
        this.initialEntities = new ArrayList<>(initialEntities);
    }

    @FXML
    public void initialize(String objectiveList) {
        Image ground = new Image("/dirt_0_new.png");
        for (int x = 0; x < dungeon.getWidth(); x++) {
            for (int y = 0; y < dungeon.getHeight(); y++) {
                squares.add(new ImageView(ground), x, y);
            }
        }

        for (ImageView entity : initialEntities)
            squares.getChildren().add(entity);
        
        dungeon.setTotalEnemies();
        dungeon.updateBoulderObjective();
        collectedSwitch.textProperty().bindBidirectional(dungeon.getPlateInfo());
        collectedEnemy.textProperty().bindBidirectional(dungeon.getEnemyInformation());
        totalTrez.setText(Integer.toString(dungeon.getNumTreasures()));
        collectedTrez.textProperty().bindBidirectional(player.getColTreasureFX());
        swordLabel.setText("None");
    	player.getWeaponName().bindBidirectional(swordLabel.textProperty());
    	totalLives.textProperty().bindBidirectional(player.getLivesProperty(), new NumberStringConverter());
    	swordLabel2.setText("None");
    	player.getBombCount().bindBidirectional(swordLabel2.textProperty());
    	swordLabel3.setText("You don't have potion");
    	player.getPotionStateInfo().bindBidirectional(swordLabel3.textProperty());
    	jsonObjectivesLabel.textProperty().setValue(objectiveList);
    	keyRed.visibleProperty().bind(Bindings.when(
    	        player.getKeyIdProperty().isEqualTo(2))
    	        .then(true)
    	        .otherwise(false));
    	keyBlue.visibleProperty().bind(Bindings.when(
    	        player.getKeyIdProperty().isEqualTo(0))
    	        .then(true)
    	        .otherwise(false));
    	keyGreen.visibleProperty().bind(Bindings.when(
    	        player.getKeyIdProperty().isEqualTo(1))
    	        .then(true)
    	        .otherwise(false));
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
        	instructionScreen.start();
        	break;
        default:
            break;
        }
        if (dungeon.isComplete()) {
        	finishedLevelScreen.start();
        }
        
        if (!dungeon.getPlayer().isAlive().getValue()) {
        	failedLevelScreen.start();
        }
        
    }
    
    public void setMenuScreen(DungeonMenuScreen menuScreen) {
		this.menuScreen = menuScreen;
	}

	public void setFinishedLevelScreen(FinishedLevelScreen finishedLevelScreen) {
		this.finishedLevelScreen = finishedLevelScreen;
	}
	
	public void setFailedLevelScreen(FailedLevelScreen failedLevelScreen) {
		this.failedLevelScreen = failedLevelScreen;
	}
	public void setInstructionScreen(InstructionScreen instructionSc) {
		this.instructionScreen = instructionSc;
	}
	
	public void setAllScreens(DungeonGameScreen gameScreen, DungeonStartScreen startScreen) {
		this.gameScreen = gameScreen;
		this.startScreen = startScreen;
	}
}

