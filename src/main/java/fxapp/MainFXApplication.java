package fxapp;

// java standard library imports
import java.io.FileInputStream;
import java.io.IOException;

// javafx imports
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

// this project imports
import controller.Controller;
import controller.GameplayScreenController;
import model.Map;

/**
 * TODO
 */
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
     * NOTE: does NOT setup the controller, that must
     * be done in the method that calls this method
     * We don't setup the controller in this method
     * because sometimes specific controllers need to
     * perform operations or set variables etc before the
     * setupController method is run and that ordering must be
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

            // grab the controller and hand it back to the specific show
            // screen method in case they need to do something
            // to it that depends on the view being shown and/or
            // the controller being loaded
            Controller controller = loader.getController();
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

        // setup the controller
        controller.setupController();

        // set general title
        mainScreen.setTitle("AdvancedImproved");
    }

    /**
     * shows the MapSelectScreen
     */
    public static void showMapSelectScreen() {
        // show the view for the Map Select Screen
        Controller controller = showScreen("src/main/java/view/MapSelectScreen.fxml");

        // setup the controller
        controller.setupController();

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

        // setup the controller after we have set the map
        // we need to know which map to put on the screen duh
        controller.setupController();

        // set general title
        mainScreen.setTitle("AdvancedImproved");
    }
}
