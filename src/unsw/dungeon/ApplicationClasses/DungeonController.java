package unsw.dungeon.ApplicationClasses;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import javafx.beans.binding.Bindings;
import javafx.beans.property.Property;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.util.converter.NumberStringConverter;
import unsw.dungeon.Dungeon;
import unsw.dungeon.Observer;
import unsw.dungeon.Subject;
import unsw.dungeon.entities.Bomb;
import unsw.dungeon.entities.BossEnemy;
import unsw.dungeon.entities.Entity;
import unsw.dungeon.entities.Orb;
import unsw.dungeon.entities.Player;

/**
 * A JavaFX controller for the dungeon.
 * @author Robert Clifton-Everest
 *
 */
public class DungeonController implements Observer {

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
    private Label bossLabel;

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
        
        findBossEnemy();
        player.registerObserver(this);
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
    public void findBossEnemy() {
    	for (Entity e : dungeon.getEntities()) {
    		if (e instanceof BossEnemy) {
    			((BossEnemy) e).registerObserver(this);
    	    	bossLabel.setVisible(true);
    		}
    	}
    }
    /**
     * Handles player key press in game
     * @param event
     */
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
        
        else if (!dungeon.getPlayer().isAlive().getValue()) {
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

	/**
	 * Gets updated when a bossEnemy spawns an orb or a bomb
	 * so we can update the view in the the front end.
	 */
	@Override
	public void update(Subject o) {
		if (dungeon.getComplete()) {
			return;
		}
		if (o instanceof Player) {
			if (((Player) o).isAlive().getValue() == false) {
	        	failedLevelScreen.start();
			}
		}
		if (o instanceof BossEnemy) {
			Bomb bomb = ((BossEnemy) o).getLastBomb();
			if (bomb != null) {
				Image unlitBImage = new Image("bomb_unlit.png");
				ImageView view = new ImageView(unlitBImage);
				view.visibleProperty().bindBidirectional(bomb.isAlive());
				trackPositionBomb(bomb, view);
				trackPosition(bomb, view);
				squares.getChildren().add(view);
			}
			Orb orb = ((BossEnemy) o).getLastOrb();
			if (orb != null) {
				Image orbImage = new Image("ball.png");
				ImageView view = new ImageView(orbImage);
				view.visibleProperty().bindBidirectional(orb.isAlive());
				trackPosition(orb, view);
				squares.getChildren().add(view);
			}
		}
		
	}
	/**
	 * Tracks the position of an entity in the front end.
	 * @param entity
	 * @param node
	 */
	private void trackPosition(Entity entity, Node node) {
        GridPane.setColumnIndex(node, entity.getX());
        GridPane.setRowIndex(node, entity.getY());
        entity.x().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable,
                    Number oldValue, Number newValue) {
                GridPane.setColumnIndex(node, newValue.intValue());
            }
        });
        entity.y().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable,
                    Number oldValue, Number newValue) {
                GridPane.setRowIndex(node, newValue.intValue());
            }
        });
    }
	/**
	 * Tracks fuselength of a bomb in the front end.
	 * @param bomb
	 * @param node
	 */
	private void trackPositionBomb(Bomb bomb, Node node) {
    	bomb.getBombState().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable,
                    Number oldValue, Number newValue) {
            	Image bombLit1Image = new Image("bomb_lit_1.png");
                Image bombLit2Image = new Image("bomb_lit_2.png");
                Image bombLit3Image = new Image("bomb_lit_3.png");
                Image bombLit4Image = new Image("bomb_lit_4.png");
            	if (newValue.intValue() == 3) {
            		((ImageView) node).setImage(bombLit1Image);
            	} else if (newValue.intValue() == 2) {
            		((ImageView) node).setImage(bombLit2Image);
            	} else if (newValue.intValue() == 1) {
            		((ImageView) node).setImage(bombLit3Image);
            	} else if (newValue.intValue() == 0) {
            		((ImageView) node).setImage(bombLit4Image);
            	} else if (newValue.intValue() == -1) {
            		bomb.setAlive(false);
            	}
            }
        });
    }
	
}

