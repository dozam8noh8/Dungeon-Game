package unsw.dungeon.ApplicationClasses;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class DungeonMenuController {

	private DungeonGameScreen firstScreen;
	private DungeonStartScreen startScreen;
    @FXML
    private Button firstLevel;
    @FXML
    private Button secondLevel;
    @FXML
    private Button bossButton;
    @FXML
    private Button lastBuiltButton;

    @FXML
    private TextField customTextField;
    
    @FXML
    private Button mazeButton;
    @FXML
    private Button builderButton;
    
    private JSONBuilderScreen builderScreen;

    @FXML
    void handleBuilderButton(ActionEvent event) {
    	builderScreen.start();
    }

    @FXML
    void handleLastBuiltLevel(ActionEvent event) {
    	try {
			firstScreen.setLastBuiltGame();
		} catch (IOException e) {
			e.printStackTrace();
		}
		firstScreen.start();
    }

    @FXML
    void handleFirstLevel(ActionEvent event) {
    	try {
			firstScreen.setFirstGame();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	firstScreen.start();
    }
	public void setFirstScreen(DungeonGameScreen firstGameScreen) {
		this.firstScreen = firstGameScreen;
	}
	
	public void setStartScreen(DungeonStartScreen startScreen) {
		this.startScreen = startScreen;
	}
	
	public void setBuilder(JSONBuilderScreen builderScreen) {
		this.builderScreen = builderScreen;
	}
	
	@FXML
	void handleSecondLevel(ActionEvent event) {
		try {
			firstScreen.setSecondGame();
		} catch (IOException e) {
			e.printStackTrace();
		}
		firstScreen.start();
	}
	@FXML
	void handleMazeButton(ActionEvent event) {
		try {
			firstScreen.setMazeGame();
		} catch (IOException e) {
			e.printStackTrace();
		}
		firstScreen.start();
	}
	@FXML
	void handleBossButton(ActionEvent event) {
		try {
			firstScreen.setBossGame();
		} catch (IOException e) {
			e.printStackTrace();
		}
		firstScreen.start();
	}
	@FXML
	void handleCustomButton (ActionEvent event) {
		try {
			firstScreen.setCustomGame(customTextField.getText());
		} catch (IOException e) {
			e.printStackTrace();
		}
		firstScreen.start();
	}
	@FXML
	void handleBackButton(ActionEvent event) {
		startScreen.start();
	}
}