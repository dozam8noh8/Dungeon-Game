package unsw.dungeon.ApplicationClasses;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class JSONBuilderScreen {
	private Stage stage;
	private String title;
	private LevelBuilderController controller;
	private DungeonMenuScreen startScreen;
	private Scene scene;
	
	public JSONBuilderScreen(Stage stage) throws IOException {
		this.stage = stage;
		title = "Dungeon Game Instruction Screen";
		controller = new LevelBuilderController();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("levelBuilder.fxml"));
		loader.setController(controller);
		Parent root = loader.load();
		scene = new Scene(root);
	}
	
	public void start() {
		stage.setTitle(title);
		stage.setScene(scene);
		stage.show();
	}
	public LevelBuilderController getController () {
		return controller;
	}
}
