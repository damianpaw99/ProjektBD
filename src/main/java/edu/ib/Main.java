package edu.ib;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Main class starting javaFX applicartion
 */
public class Main extends Application {
    /**
     * Main method run on start
     *
     * @param args main arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Method called after main
     *
     * @param primaryStage Stage to show
     * @throws IOException Exception from FXMLLOADER.load()
     */
    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/main_menu.fxml"));
        Scene scene = new Scene(root, 1000, 800);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
