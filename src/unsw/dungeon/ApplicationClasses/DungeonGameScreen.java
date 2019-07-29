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
	
	public DungeonGameScreen(Stage primaryStage) throws IOException {
		this.stage = primaryStage;
		this.title = "Dungeon Game Screen";
		primaryStage.setTitle(title);
        setFirstGame();
	}

	public void setFirstGame() throws IOException {
		DungeonControllerLoader dungeonLoader = new DungeonControllerLoader("advanced.json");
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
	}

	public void setMenuScreen(DungeonMenuScreen menuScreen) {
		controller.setMenuScreen(menuScreen);
		this.menuScreen = menuScreen;
	}

}
