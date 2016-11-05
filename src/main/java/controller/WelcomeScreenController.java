package controller;

import javafx.fxml.FXML;
import fxapp.MainFXApplication;

/**
 * Controller for welcome screen
 */
public class WelcomeScreenController {

    /**
     * executes when start button is pressed
     */
    @FXML
    private void welcomeScreenStartButtonPressed() {
        MainFXApplication.showMapSelectScreen();
    }
}
