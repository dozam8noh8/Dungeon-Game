import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import org.json.JSONException;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;

public class LevelBuilderController {
	private JSONLevelBuilder jb;
	private IntegerProperty currX = new SimpleIntegerProperty(1);
	private IntegerProperty currY = new SimpleIntegerProperty(1);
	private Image playerImage;
    private Image wallImage;
    private Image exitImage;
    private Image enemyImage;
    private Image treasureImage;
    private Image unlitBImage;
    private Image boulderImage;
    private Image switchImage;
    private Image keyImage;
    private Image doorImage;
    private Image potionImage;
    private Image swordImage;
    
    private String ObjString  = " ";
    @FXML
    private Button enemyObjButton;
    @FXML
    private Label FEObjString;

    @FXML
    private Button treasureObjButton;

    @FXML
    private Button boulderObjButton;

    @FXML
    private Button exitObjButton;

    @FXML
    private Button ANDObjButton;
    @FXML
    private Label allDone;

    @FXML
    private Button ORObjButton;
	private GridPane levelGrid;
	 @FXML
    private Button boulderButton;
	 @FXML
	 private Button bombButton;
	 @FXML
	 private Button potionButton;
	 @FXML
	 private Button wallButton;
    @FXML
    private Button switchButton;

    @FXML
    private Button keyButton;

    @FXML
    private Button doorButton;

    @FXML
    private TextField IDField;
 	@FXML
    private Button enemyButton;
 	@FXML
 	private Pane gridPaneBase;
    @FXML
    private Button treasureButton;

    @FXML
    private Button startBuildButton;

    @FXML
    private TextField fileTextField;

    @FXML
    private Button endButton;

    @FXML
    private Button startEntityButton;

    @FXML
    private Button endEntityButton;

    @FXML
    private Button startObjectivesButton;

    @FXML
    private Button endObjectivesButton;

    @FXML
    private TextField heightField;

    @FXML
    private TextField widthField;

    @FXML
    private TextField xCoordField;

    @FXML
    private TextField yCoordField;

    @FXML
    private Button restartButton;
    @FXML
    private Button finishObjButton;
	LevelBuilderController(){
        playerImage = new Image("/human_new.png");
        wallImage = new Image("/brick_brown_0.png");
        exitImage = new Image("/exit.png");
        enemyImage = new Image("deep_elf_master_archer.png");
        treasureImage = new Image("gold_pile.png");
        unlitBImage = new Image("bomb_unlit.png");
        boulderImage = new Image("boulder.png");
        switchImage = new Image("pressure_plate.png");
        keyImage = new Image("key.png");
        doorImage = new Image("closed_door.png");
        potionImage = new Image("bubbly.png");
        swordImage = new Image("greatsword_1_new.png");
        switchImage = new Image("pressure_plate.png");
        
	}

	public void bindXY() {
		System.out.println(yCoordField);
		xCoordField.textProperty().bindBidirectional(currX, new NumberStringConverter());
		yCoordField.textProperty().bindBidirectional(currY, new NumberStringConverter());
		//currX.bindBidirectional(xCoordField.textProperty());
	}
    @FXML
    void handleEndButton(ActionEvent event) {
    	jb.endDungeon();
    	jb.closeWriter();
    	popup();
    }

    @FXML
    void handleEndEntityButton(ActionEvent event) {
    	jb.endEntityList();
    	startObjectivesButton.setDisable(false);
    	endEntityButton.setDisable(true);
    	disableEntityButtons(true);
    	
    }

    @FXML
    void handleEndObjectivesButton(ActionEvent event) {
    	disableObjButtons(true);
    	endButton.setDisable(false);

    }
    @FXML
    void handleBombButton(ActionEvent event) {
    	jb.makeBomb(currX.getValue(),currY.getValue());
    	levelGrid.add(new ImageView(unlitBImage), currX.getValue(), currY.getValue()); 
    	incrementXY();
    }
    @FXML
    void handleEnemyButton(ActionEvent event) {
    	jb.makeEnemy(currX.getValue(),currY.getValue());
    	levelGrid.add(new ImageView(enemyImage), currX.getValue(), currY.getValue()); 
    	incrementXY();
    }
	public void makePlayer() {
		jb.makePlayer(currX.getValue(), currY.getValue());
		levelGrid.add(new ImageView(playerImage), currX.getValue(), currY.getValue());
		incrementXY();
	}
    
    public void incrementXY() {
    	currX.setValue(currX.getValue() + 1);
    	//y++;
    	if (currX.getValue() >= jb.getDungeonWidth()) {
    		currX.setValue(0);
        	currY.setValue(currY.getValue() + 1);
    	}
    	
    	if (currY.getValue() >= jb.getDungeonHeight()) {
    		currY.setValue(0);
    	}
    	
    	
    }
    @FXML
    void initialize () {
    	bindXY();
    	disableEntityButtons(true);
    }
    public void popup() {
    	allDone.setVisible(true);
    }
    
    public void disableEntityButtons(boolean disable) {
    	treasureButton.setDisable(disable);
    	enemyButton.setDisable(disable);
    	switchButton.setDisable(disable);
    	boulderButton.setDisable(disable);
    	wallButton.setDisable(disable);
    	potionButton.setDisable(disable);
    	keyButton.setDisable(disable);
    	doorButton.setDisable(disable);
    	bombButton.setDisable(disable);

    }
    public void disableObjButtons(boolean disable) {
    	treasureObjButton.setDisable(disable);
    	exitObjButton.setDisable(disable);
    	enemyObjButton.setDisable(disable);
    	boulderObjButton.setDisable(disable);
    	ANDObjButton.setDisable(disable);
    	ORObjButton.setDisable(disable);
    	finishObjButton.setDisable(disable);

    }
    public void initialiseGridPane(int x, int y) {
    	System.out.println("Doing somethign");
    		System.out.println(x);
		levelGrid = new GridPane();
		gridPaneBase.getChildren().add(levelGrid);
		Image dirt = new Image("/dirt_0_new.png");
		for (int i = 0; i < x; i++) {
			for (int j = 0; j < y; j++) {
				levelGrid.add(new ImageView(dirt), i, j);
			}
		}
    }
    @FXML
    void handleStartBuildButton(ActionEvent event) {
    	try {
			jb = new JSONLevelBuilder("custom" + fileTextField.getText() + ".json");
			int xDim = Integer.parseInt(heightField.getText());
			int yDim = Integer.parseInt(widthField.getText());
			jb.makeDungeonDimensions(xDim, yDim);
			initialiseGridPane(xDim, yDim);
			startEntityButton.setDisable(false);
			fileTextField.setDisable(true);
			heightField.setDisable(true);
			widthField.setDisable(true);
			
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
    }

    @FXML
    void handleStartEntityButton(ActionEvent event) {
    	jb.startEntityList();
    	makePlayer();
		disableEntityButtons(false);
		endEntityButton.setDisable(false);
		startEntityButton.setDisable(true);
    }
    @FXML
    void handleStartObjectivesButton(ActionEvent event) {
    	jb.startObjective();
    	startObjectivesButton.setDisable(true);
    	endObjectivesButton.setDisable(false);
    	disableObjButtons(false);
    }

    @FXML
    void handleTreasureButton(ActionEvent event) {
    	jb.makeTreasure(currX.getValue(),currY.getValue());
    	levelGrid.add(new ImageView(treasureImage), currX.getValue(), currY.getValue()); 
    	incrementXY();
    }
    @FXML
    void handlePotionButton(ActionEvent event) {
    	jb.makePotion(currX.getValue(),currY.getValue());
    	levelGrid.add(new ImageView(potionImage), currX.getValue(), currY.getValue()); 
    	incrementXY();
    }
    @FXML
    void handleBoulderButton(ActionEvent event) {
    	jb.makeBoulder(currX.getValue(),currY.getValue());
    	levelGrid.add(new ImageView(boulderImage), currX.getValue(), currY.getValue()); 
    	incrementXY();
    }
    @FXML
    void handleSwitchButton(ActionEvent event) {
    	jb.makeSwitch(currX.getValue(),currY.getValue());
    	levelGrid.add(new ImageView(switchImage), currX.getValue(), currY.getValue()); 
    	incrementXY();
    }
    @FXML
    void handleKeyButton(ActionEvent event) {
    	jb.makeKey(currX.getValue(),currY.getValue(), Integer.parseInt(IDField.getText()));
    	levelGrid.add(new ImageView(keyImage), currX.getValue(), currY.getValue()); 
    	incrementXY();
    }
    @FXML
    void handleDoorButton(ActionEvent event) {
    	jb.makeDoor(currX.getValue(),currY.getValue(), Integer.parseInt(IDField.getText()));
    	levelGrid.add(new ImageView(doorImage), currX.getValue(), currY.getValue()); 
    	incrementXY();
    }
    @FXML
    void handleWallButton(ActionEvent event) {
    	jb.makeWall(currX.getValue(),currY.getValue());
    	levelGrid.add(new ImageView(wallImage), currX.getValue(), currY.getValue()); 
    	incrementXY();
    }
    @FXML
    void handleEnemyObjButton(ActionEvent event) {
    	jb.makeEnemyObjective();
    	ObjString += "Kill all enemies\n";
    	FEObjString.textProperty().setValue(ObjString);
    	
    }

    @FXML
    void handleExitObjButton(ActionEvent event) {
    	jb.makeExitObjective();
    	ObjString += "Walk through Exit\n";
    	FEObjString.textProperty().setValue(ObjString);
    }
    @FXML
    void handleBoulderObjButton(ActionEvent event) {
    	jb.makeBoulderObjective();
    	ObjString += "Boulders on Switches\n";
    	FEObjString.textProperty().setValue(ObjString);
    }
    @FXML
    void handleTreasureObjButton(ActionEvent event) {
    	jb.makeTreasureObjective();
    	ObjString += "Collect all treasure\n";
    	FEObjString.textProperty().setValue(ObjString);
    }
    @FXML
    void handleANDButton(ActionEvent event) {
    	jb.startAndObjective();
    	ObjString += "AND {\n";
    	FEObjString.textProperty().setValue(ObjString);
    }
    @FXML
    void handleORButton(ActionEvent event) {
    	jb.startOrObjective();
    	ObjString += "OR {\n";
    	FEObjString.textProperty().setValue(ObjString);
    }
    @FXML
    void handleFinishObjButton(ActionEvent event) {
    	jb.endObjective();
    	ObjString += "}\n";
    	FEObjString.textProperty().setValue(ObjString);
    }
    
    

}
