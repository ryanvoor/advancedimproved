package fxapp;

import model.Map;
import controller.GameplayScreenController;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;

public class MainFXApplication extends Application {

    /**
     * the main container for the application window
     */
    private static Stage mainScreen;

    /**
     * main method for main application class
     * @param args command line parameters
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        mainScreen = primaryStage;
        showWelcomeScreen();
    }

    /**
     * TODO
     */
    private static void showScreen(String fxmlFilePath) {
        try {
            // load welcome screen.
            FXMLLoader loader = new FXMLLoader();
            BorderPane screen = loader.load(
                new FileInputStream(fxmlFilePath));

            // show the scene containing the root layout.
            Scene scene = new Scene(screen);
            mainScreen.setScene(scene);
            mainScreen.show();

        } catch (IOException e) {
            //error on load, so log it
            e.printStackTrace();
        }
    }

    /**
     * Setup our default application view that is shown on application startup
     * This is displayed in the startup window
     */
    public static void showWelcomeScreen() {
        showScreen("src/main/java/view/WelcomeScreen.fxml");
        mainScreen.setTitle("AdvancedImproved");
    }

    /**
     * shows the MapSelectScreen
     */
    public static void showMapSelectScreen() {
        showScreen("src/main/java/view/MapSelectScreen.fxml");
    }

    /**
     * shows the GameplayScreen
     */
    public static void showGameplayScreen(Map map) {
        showScreen("src/main/java/view/GameplayScreen.fxml");
        GameplayScreenController.setMap(map);
    }
}
