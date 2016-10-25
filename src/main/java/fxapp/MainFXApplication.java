package fxapp;

import controller.WelcomeScreenController;

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
        showWelcomeScreen(mainScreen);
    }

    /**
     * Setup our default application view that is shown on application startup
     * This is displayed in the startup window
     * precondition - the main stage is already initialized and
     * showing (initRootLayout has been called)
     * preconditions - the view is initialized and displayed
     */
    public static void showWelcomeScreen(Stage mainScreen) {
        try {
            // Load welcome screen.
            FXMLLoader loader = new FXMLLoader();
            BorderPane welcomeScreen = loader.load(
                new FileInputStream("src/main/java/view/WelcomeScreen.fxml"));

            // Give the controller access to the main app.
            WelcomeScreenController controller = loader.getController();

            // Show the scene containing the root layout.
            mainScreen.setTitle("AdvancedImproved");
            Scene scene = new Scene(welcomeScreen);
            mainScreen.setScene(scene);
            mainScreen.show();

        } catch (IOException e) {
            //error on load, so log it
            e.printStackTrace();
        }
    }
}
