package unsw.dungeon.ApplicationClasses;


import java.io.IOException;
import javafx.application.Application;
import javafx.stage.Stage;

public class StartDungeonApplication extends Application{

	@Override
	public void start(Stage primaryStage) throws IOException {
		DungeonStartScreen startScreen = new DungeonStartScreen(primaryStage);
		DungeonMenuScreen menuScreen = new DungeonMenuScreen(primaryStage);
		DungeonGameScreen firstGameScreen = new DungeonGameScreen(primaryStage);
		menuScreen.getController().setFirstScreen(firstGameScreen);
		InstructionScreen instructionScreen = new InstructionScreen(primaryStage);
		startScreen.getController().setInstructionScreen(instructionScreen);
		startScreen.getController().setMenuScreen(menuScreen);
		instructionScreen.getController().setStartScreen(startScreen);
		startScreen.start();
		

	}
    public static void main(String[] args) {
        launch(args);
    }
}
