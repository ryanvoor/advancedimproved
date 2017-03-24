package fxapp;

import model.Map;
import controller.Controller;
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
     * General method that shows any view passed into it
     * This method also sets the scene and returns the
     * controller for that view
     * NOTE: does NOT initialize the controller, that must
     * be done in the method that calls this method
     * We don't initialize the controller in this method
     * because sometimes specific controllers need to
     * perform operations or set variables etc before the
     * initialize method is run and that ordering must be
     * controller in the controller specific methods
     */
    private static Controller showScreen(String fxmlFilePath) {
        try {
            // load welcome screen.
            FXMLLoader loader = new FXMLLoader();
            BorderPane screen = loader.load(
                new FileInputStream(fxmlFilePath));

            // show the scene containing the root layout.
            Scene scene = new Scene(screen);
            mainScreen.setScene(scene);
            mainScreen.show();

            // grab screen controller
            Controller controller = loader.getController();

            // hand controller back to the specific show
            // screen method in case they need to do something
            // to it that depends on the view being shown and/or
            // the controller being loaded
            return controller;

        } catch (IOException e) {
            //error on load, so log it
            e.printStackTrace();
        }

        // this will only happen if we catch an error
        // which should only happen if we misnamed a
        // view file or something like that
        return null;
    }

    /**
     * Setup our default application view that is shown on application startup
     * This is displayed in the startup window
     */
    public static void showWelcomeScreen() {
        // show the view for the Welcome Screen
        Controller controller = showScreen("src/main/java/view/WelcomeScreen.fxml");

        // initialize the controller
        controller.initialize();

        // set general title
        mainScreen.setTitle("AdvancedImproved");
    }

    /**
     * shows the MapSelectScreen
     */
    public static void showMapSelectScreen() {
        // show the view for the Map Select Screen
        Controller controller = showScreen("src/main/java/view/MapSelectScreen.fxml");

        // initialize the controller
        controller.initialize();

        // set the title for the map select screen
        mainScreen.setTitle("Select a Map!");
    }

    /**
     * shows the GameplayScreen
     */
    public static void showGameplayScreen(Map map) {
        // show the view for the Gameplay Screen
        GameplayScreenController controller
            = (GameplayScreenController) showScreen("src/main/java/view/GameplayScreen.fxml");

        // set the map so we know which map to load onto the screen
        controller.setMap(map);

        // initialize the controller after we have set the map
        // we need to know which map to put on the screen duh
        controller.initialize();

        // set general title
        mainScreen.setTitle("AdvancedImproved");
    }
}
