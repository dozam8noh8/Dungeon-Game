package unsw.dungeon.ApplicationClasses;


import java.io.IOException;
import javafx.application.Application;
import javafx.stage.Stage;

public class StartDungeonApplication extends Application{

	@Override
	public void start(Stage primaryStage) throws IOException {
		DungeonStartScreen startScreen = new DungeonStartScreen(primaryStage);
		DungeonMenuScreen menuScreen = new DungeonMenuScreen(primaryStage);
		FinishedLevelScreen finishedLevelScreen = new FinishedLevelScreen(primaryStage);
		finishedLevelScreen.getController().setMenuScreen(menuScreen);
		FailedLevelScreen failedLevelScreen = new FailedLevelScreen(primaryStage);
		failedLevelScreen.getController().setMenuScreen(menuScreen);
		DungeonGameScreen firstGameScreen = new DungeonGameScreen(primaryStage);
		menuScreen.getController().setFirstScreen(firstGameScreen);
		
		firstGameScreen.setMenuScreen(menuScreen);
		firstGameScreen.setFailedLevelScreen(failedLevelScreen);
		firstGameScreen.setFinishedLevelScreen(finishedLevelScreen);
		InstructionScreen instructionScreen = new InstructionScreen(primaryStage);
		firstGameScreen.setInstructionScreen(instructionScreen);

		startScreen.getController().setInstructionScreen(instructionScreen);
		startScreen.getController().setMenuScreen(menuScreen);
		instructionScreen.getController().setStartScreen(startScreen);
		// Adding levelbuilder screen
		JSONBuilderScreen builderScreen = new JSONBuilderScreen(primaryStage);
		menuScreen.getController().setBuilder(builderScreen);
		builderScreen.getController().setMenuScreen(menuScreen);
		menuScreen.getController().setStartScreen(startScreen);
		firstGameScreen.getController().setInstructionScreen(instructionScreen);
		firstGameScreen.getController().setAllScreens(firstGameScreen, startScreen);
		startScreen.start();
	}
    public static void main(String[] args) {
        launch(args);
    }
}
