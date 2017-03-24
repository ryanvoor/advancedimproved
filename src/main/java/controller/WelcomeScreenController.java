package controller;

import javafx.fxml.FXML;
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
