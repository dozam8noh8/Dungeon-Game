package unsw.dungeon.ApplicationClasses;


import java.io.IOException;
import javafx.application.Application;
import javafx.stage.Stage;

public class StartDungeonApplication extends Application{

	@Override
	public void start(Stage primaryStage) throws IOException {
		DungeonStartScreen startScreen = new DungeonStartScreen(primaryStage);
		InstructionScreen instructionScreen = new InstructionScreen(primaryStage);
		startScreen.getController().setInstructionScreen(instructionScreen);
		instructionScreen.getController().setStartScreen(startScreen);
		startScreen.start();
		

	}
    public static void main(String[] args) {
        launch(args);
    }
}
