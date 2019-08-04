package unsw.dungeon.ApplicationClasses;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.json.JSONObject;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DungeonGameScreen {
	private Stage stage;
	private String title;
	private Scene scene;
	private DungeonController controller;
	DungeonControllerLoader dungeonLoader;
	FXMLLoader loader;
	private DungeonMenuScreen menuScreen;
	private FailedLevelScreen failedLevelScreen;
	private FinishedLevelScreen finishedLevelScreen;
	private InstructionScreen instructionScreen;
	
	public DungeonGameScreen(Stage primaryStage) throws IOException {
		this.stage = primaryStage;
		this.title = "Dungeon Game Screen";
		primaryStage.setTitle(title);
        setFirstGame();
	}

	public void setFirstGame() throws IOException {
		DungeonControllerLoader dungeonLoader = new DungeonControllerLoader("advanced.json");
        setGameLoader(dungeonLoader);
	}
	public void setCustomGame(String levelName) throws IOException {
		DungeonControllerLoader dungeonLoader = new DungeonControllerLoader(levelName);
        setGameLoader(dungeonLoader);
	}
	private void setGameLoader(DungeonControllerLoader dungeonLoader) throws FileNotFoundException, IOException {
		DungeonController controller = dungeonLoader.loadController();
        loader = new FXMLLoader(getClass().getResource("DungeonView.fxml"));
        loader.setController(controller);
        Parent root = loader.load();
        Scene scene = new Scene(root);
        this.scene = scene;
        root.requestFocus();
        stage.setScene(scene);
        stage.show();
        this.controller = controller;
		String objectiveList = dungeonLoader.getObjString();
        controller.initialize(objectiveList);
        //setAllScreen(menuScreen, failedLevelScreen, finishedLevelScreen);
        setMenuScreen(menuScreen);
        setFailedLevelScreen(failedLevelScreen);
        setFinishedLevelScreen(finishedLevelScreen);
        setInstructionScreen(instructionScreen);
	}
	
	public void start() {
		instructionScreen.getController().setEnableButton(false);
		instructionScreen.getController().setGameScreen(this);
		stage.setTitle(title);
		stage.setScene(scene);
		stage.show();
	}
	public DungeonController getController () {
		return controller;
	}
	/**
	 * Set the first game screen.
	 * @throws IOException
	 */
	public void setSecondGame() throws IOException {
		DungeonControllerLoader dungeonLoader = new DungeonControllerLoader("boulders.json");
		setGameLoader(dungeonLoader);
	}
	/**
	 * Set the maze game screen.
	 * @throws IOException
	 */
	public void setMazeGame() throws IOException {
		DungeonControllerLoader dungeonLoader = new DungeonControllerLoader("maze.json");
		setGameLoader(dungeonLoader);
	}
	/**
	 * Set the boss game screen.
	 * @throws IOException
	 */
	public void setBossGame() throws IOException {
		DungeonControllerLoader dungeonLoader = new DungeonControllerLoader("boss.json");
		setGameLoader(dungeonLoader);
	}
	
	public void setAllScreen(DungeonMenuScreen menuScreen, FailedLevelScreen failedLevelScreen, FinishedLevelScreen finishedLevelScreen) {
		controller.setMenuScreen(menuScreen);
		this.menuScreen = menuScreen;
		controller.setFailedLevelScreen(failedLevelScreen);
		this.menuScreen = menuScreen;
		controller.setFinishedLevelScreen(finishedLevelScreen);
		this.menuScreen = menuScreen;
	}
	/**
	 * Sets the menu screen so we can go back in game.
	 * @param menuScreen
	 */
	public void setMenuScreen(DungeonMenuScreen menuScreen) {
		controller.setMenuScreen(menuScreen);
		this.menuScreen = menuScreen;
		this.failedLevelScreen = failedLevelScreen;
		this.finishedLevelScreen = finishedLevelScreen;
		this.instructionScreen = instructionScreen;
	}
	/**
	 * Sets the instruction screen so we can view it in game.
	 * @param instructionScreen
	 */
	public void setInstructionScreen(InstructionScreen instructionScreen) {
		controller.setInstructionScreen(instructionScreen);
		this.instructionScreen = instructionScreen;
		this.menuScreen = menuScreen;
		this.failedLevelScreen = failedLevelScreen;
		this.finishedLevelScreen = finishedLevelScreen;
	}
	/**
	 * Sets level screen to transition when player dies.
	 * @param failedLevelScreen
	 */
	public void setFailedLevelScreen(FailedLevelScreen failedLevelScreen) {
		controller.setFailedLevelScreen(failedLevelScreen);
		this.menuScreen = menuScreen;
		this.failedLevelScreen = failedLevelScreen;
		this.finishedLevelScreen = finishedLevelScreen;
		this.instructionScreen = instructionScreen;
	}
	/**
	 * Sets finished level screen to transition if player wins.
	 * @param finishedLevelScreen
	 */
	public void setFinishedLevelScreen(FinishedLevelScreen finishedLevelScreen) {
		controller.setFinishedLevelScreen(finishedLevelScreen);
		this.menuScreen = menuScreen;
		this.failedLevelScreen = failedLevelScreen;
		this.finishedLevelScreen = finishedLevelScreen;
		this.instructionScreen = instructionScreen;
	}

	public void setLastBuiltGame() throws FileNotFoundException, IOException {
		DungeonControllerLoader dungeonLoader = new DungeonControllerLoader("custom1.json");
		setGameLoader(dungeonLoader);
	}
	
	

}
