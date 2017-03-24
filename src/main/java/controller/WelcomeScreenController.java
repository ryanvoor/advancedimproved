package controller;

// javafx imports
import javafx.fxml.FXML;

// this project imports
import fxapp.MainFXApplication;

/**
 * Controller for Welcome Screen
 */
public class WelcomeScreenController extends Controller {

    /**
     * executes when start button is pressed
     */
    @FXML
    private void welcomeScreenStartButtonPressed() {
        MainFXApplication.showMapSelectScreen();
    }
}
