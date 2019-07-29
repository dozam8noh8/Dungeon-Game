package unsw.dungeon.ApplicationClasses;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class StartController {

    @FXML
    private Button startButton;
    @FXML
    private Button instructionsButton;
	private InstructionScreen instructionScreen;
	private DungeonMenuScreen menuScreen;

    @FXML
    void handleStartButton(ActionEvent event) {
    	menuScreen.start();
    }

    @FXML
    void handleInstructionsButton(ActionEvent event) {
    	instructionScreen.start();
    }
    public void setInstructionScreen(InstructionScreen c) {
		this.instructionScreen = c;
	}

	public void setMenuScreen(DungeonMenuScreen menuScreen) {
		this.menuScreen = menuScreen;
	}

}
