package unsw.dungeon.ApplicationClasses;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class InstructionScreen {
	private Stage stage;
	private String title;
	private InstructionController controller;
	private DungeonStartScreen startScreen;
	private Scene scene;
	
	public InstructionScreen(Stage stage) throws IOException {
		this.stage = stage;
		title = "Dungeon Game Instruction Screen";
		controller = new InstructionController();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("instructions.fxml"));
		loader.setController(controller);
		Parent root = loader.load();
		scene = new Scene(root);
		
		
	}
	public void start() {
		stage.setTitle(title);
		stage.setScene(scene);
		stage.show();
	}
	public InstructionController getController () {
		return controller;
	}
	
}
