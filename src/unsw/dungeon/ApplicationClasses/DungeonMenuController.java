package unsw.dungeon.ApplicationClasses;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class DungeonMenuController {

	private DungeonGameScreen firstScreen;
    @FXML
    private Button firstLevel;
    @FXML
    private Button secondLevel;
    
    @FXML
    private Button customLevel;

    @FXML
    private Button builderButton;
    
    private JSONBuilderScreen builderScreen;

    @FXML
    void handleBuilderButton(ActionEvent event) {
    	builderScreen.start();
    }

    @FXML
    void handleCustomLevel(ActionEvent event) {
    	try {
			firstScreen.setCustomGame();
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

}