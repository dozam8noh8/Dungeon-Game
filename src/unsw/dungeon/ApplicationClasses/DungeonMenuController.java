package unsw.dungeon.ApplicationClasses;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class DungeonMenuController {

	private DungeonGameScreen firstScreen;
    @FXML
    private Button firstLevel;

    @FXML
    void handleFirstLevel(ActionEvent event) {
    	firstScreen.start();
    }
	public void setFirstScreen(DungeonGameScreen firstGameScreen) {
		this.firstScreen = firstGameScreen;
	}

}