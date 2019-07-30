package unsw.dungeon.ApplicationClasses;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class FailedLevelController {

    @FXML
    private Button backButton;
    private DungeonMenuScreen menuScreen;
    
    public void setMenuScreen(DungeonMenuScreen menuScreen) {
		this.menuScreen = menuScreen;
	}
    
    @FXML
    void handleBackButton(ActionEvent event) {
    	menuScreen.start();
    }

}