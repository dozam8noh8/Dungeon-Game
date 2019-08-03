package unsw.dungeon.ApplicationClasses;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
public class InstructionController {

	private DungeonStartScreen startScreen;
	private DungeonGameScreen gameScreen;
    @FXML
    private Button backButton;
    @FXML
    private Button RetToGame;



	public void setStartScreen(DungeonStartScreen startScreen) {
		this.startScreen = startScreen;
	}
	public void setGameScreen(DungeonGameScreen gameScreen) {
		this.gameScreen = gameScreen;
	}
    @FXML
    void handleBackButton(ActionEvent event) {
    	startScreen.start();
    }
    @FXML
    void handleBackToGame(ActionEvent event) {
    	gameScreen.start();
    }
    void disableGameButton(boolean disable) {
    	RetToGame.setDisable(disable);
    }
    

}
