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

    @FXML
    void handleStartButton(ActionEvent event) {
    	System.out.println("Do something");
    }

    @FXML
    void handleInstructionsButton(ActionEvent event) {
    	instructionScreen.start();
    }
    public void setInstructionScreen(InstructionScreen c) {
		this.instructionScreen = c;
	}

}
