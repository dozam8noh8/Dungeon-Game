package unsw.dungeon.ApplicationClasses;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class FailedLevelScreen {
	private Stage stage;
	private String title;
	private FailedLevelController controller;
	private DungeonMenuScreen menuScreen;
	private Scene scene;
	
	public FailedLevelScreen(Stage stage) throws IOException {
		this.stage = stage;
		title = "Dungeon Failed Level";
		controller = new FailedLevelController();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FailedLevel.fxml"));
		loader.setController(controller);
		Parent root = loader.load();
		scene = new Scene(root);	
	}
	
	public void start() {
		stage.setTitle(title);
		stage.setScene(scene);
		stage.show();
	}
	public FailedLevelController getController () {
		return controller;
	}
}
