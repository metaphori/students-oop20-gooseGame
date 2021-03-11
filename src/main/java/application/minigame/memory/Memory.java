package application.minigame.memory;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Memory extends Application{

    private static final int SCENE_WIDTH = 610;
    private static final int SCENE_HEIGHT = 530;

    @Override
    public void start(Stage primaryStage) throws Exception {
        final Parent root = FXMLLoader.load(ClassLoader.getSystemResource("layouts/memory.fxml"));
        final Scene scene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT);

        /* Stage configuration */
        primaryStage.getIcons().add(new Image("logo.png"));
        primaryStage.setTitle("[GooseGame] Memory");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}