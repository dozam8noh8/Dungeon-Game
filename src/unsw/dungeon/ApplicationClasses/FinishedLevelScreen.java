package unsw.dungeon.ApplicationClasses;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class FinishedLevelScreen {
	private Stage stage;
	private String title;
	private FinishedLevelController controller;
	private DungeonMenuScreen menuScreen;
	private Scene scene;
	
	public FinishedLevelScreen(Stage stage) throws IOException {
		this.stage = stage;
		title = "Dungeon Finished Level";
		controller = new FinishedLevelController();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FinishedLevel.fxml"));
		loader.setController(controller);
		Parent root = loader.load();
		scene = new Scene(root);	
	}
	
	public void start() {
		stage.setTitle(title);
		stage.setScene(scene);
		stage.show();
	}
	public FinishedLevelController getController () {
		return controller;
	}
}
