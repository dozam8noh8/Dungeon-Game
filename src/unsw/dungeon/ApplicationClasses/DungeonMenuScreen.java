package unsw.dungeon.ApplicationClasses;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DungeonMenuScreen {
	private Stage stage;
	private String title;
	private DungeonGameScreen gameScreen;
	private Scene scene;
	private DungeonMenuController controller;
	
	public DungeonMenuScreen(Stage stage) throws IOException {
		this.stage = stage;
		title = "Dungeon Game Instruction Screen";
		controller = new DungeonMenuController();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("DungeonMenu.fxml"));
		loader.setController(controller);
		Parent root = loader.load();
		scene = new Scene(root);
		
		
	}
	public void start() {
		stage.setTitle(title);
		stage.setScene(scene);
		stage.show();
	}
	public DungeonMenuController getController () {
		return controller;
	}
}
