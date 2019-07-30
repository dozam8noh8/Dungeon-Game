package unsw.dungeon.ApplicationClasses;

import java.io.FileNotFoundException;
import java.io.IOException;

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
	
	public DungeonGameScreen(Stage primaryStage) throws IOException {
		this.stage = primaryStage;
		this.title = "Dungeon Game Screen";
		primaryStage.setTitle(title);
        setFirstGame();
	}

	public void setFirstGame() throws IOException {
		DungeonControllerLoader dungeonLoader = new DungeonControllerLoader("advancedWithBoulder.json");
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
        setMenuScreen(menuScreen);
        setFailedLevelScreen(failedLevelScreen);
        setFinishedLevelScreen(finishedLevelScreen);
	}
	
	public void start() {
		stage.setTitle(title);
		stage.setScene(scene);
		stage.show();
	}
	public DungeonController getController () {
		return controller;
	}
	
	public void setSecondGame() throws IOException {
		DungeonControllerLoader dungeonLoader = new DungeonControllerLoader("maze25.json");
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
        setMenuScreen(menuScreen);
        setFailedLevelScreen(failedLevelScreen);
        setFinishedLevelScreen(finishedLevelScreen);
	}

	public void setMenuScreen(DungeonMenuScreen menuScreen) {
		controller.setMenuScreen(menuScreen);
		this.menuScreen = menuScreen;
		this.failedLevelScreen = failedLevelScreen;
		this.finishedLevelScreen = finishedLevelScreen;
	}
	
	public void setFailedLevelScreen(FailedLevelScreen failedLevelScreen) {
		controller.setFailedLevelScreen(failedLevelScreen);
		this.menuScreen = menuScreen;
		this.failedLevelScreen = failedLevelScreen;
		this.finishedLevelScreen = finishedLevelScreen;
	}
	
	public void setFinishedLevelScreen(FinishedLevelScreen finishedLevelScreen) {
		controller.setFinishedLevelScreen(finishedLevelScreen);
		this.menuScreen = menuScreen;
		this.failedLevelScreen = failedLevelScreen;
		this.finishedLevelScreen = finishedLevelScreen;
	}
	
	

}
