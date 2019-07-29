package unsw.dungeon.ApplicationClasses;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DungeonStartScreen {
	private Stage stage;
	private String title;
	private StartController controller;
	private Scene scene;
	private InstructionScreen instructionScreen;
	
	public DungeonStartScreen(Stage stage) throws IOException {
		this.stage = stage;
		title = "Dungeon Game Start Screen";
		controller = new StartController();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("startScreen.fxml"));
		loader.setController(controller);
		Parent root = loader.load();
		scene = new Scene(root, 500, 300);
		
		
	}
	public void start() {
		stage.setTitle(title);
		stage.setScene(scene);
		stage.show();
	}
	public StartController getController () {
		return controller;
	}
	
}
