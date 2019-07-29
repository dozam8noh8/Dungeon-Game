package unsw.dungeon.ApplicationClasses;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
public class InstructionController {

	private DungeonStartScreen startScreen;
    @FXML
    private Button backButton;



	public void setStartScreen(DungeonStartScreen startScreen) {
		this.startScreen = startScreen;
	}
    @FXML
    void handleBackButton(ActionEvent event) {
    	startScreen.start();
    }

}
