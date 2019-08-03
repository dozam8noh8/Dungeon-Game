import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import unsw.dungeon.ApplicationClasses.DungeonController;
import unsw.dungeon.ApplicationClasses.DungeonControllerLoader;

public class JSONLevelBuilderApplication extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        primaryStage.setTitle("Level Builder");

        LevelBuilderController controller = new LevelBuilderController();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("levelBuilder.fxml"));
        loader.setController(controller);
        Parent root = loader.load();
        Scene scene = new Scene(root);
        root.requestFocus();
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
