package unsw.dungeon.ApplicationClasses;

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
	
	public DungeonGameScreen(Stage primaryStage) throws IOException {
		this.stage = primaryStage;
		this.title = "Dungeon";
		primaryStage.setTitle(title);

        DungeonControllerLoader dungeonLoader = new DungeonControllerLoader("maze26.json");

        DungeonController controller = dungeonLoader.loadController();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("DungeonView.fxml"));
        loader.setController(controller);
        Parent root = loader.load();
        Scene scene = new Scene(root);
        this.scene = scene;
        root.requestFocus();
        primaryStage.setScene(scene);
        primaryStage.show();
	}
	
	public void start() {
		stage.setTitle(title);
		stage.setScene(scene);
		stage.show();
	}
	public DungeonController getController () {
		return controller;
	}
}
