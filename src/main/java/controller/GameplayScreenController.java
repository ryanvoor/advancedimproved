package controller;

import javafx.fxml.FXML;
import model.Map;
import fxapp.MainFXApplication;
import javafx.scene.canvas.Canvas;

/**
 * TODO
 */
public class GameplayScreenController {

    // instance variables
    private static Map map;

    // FXML instance variables
    @FXML
    private Canvas mapCanvas;

    /**
     * TODO
     */
    public static void setMap(Map map) {
        GameplayScreenController.map = map;
    }

    /**
     * executes when enter key is pressed
     */
    // TODO do I need this tag here?
    @FXML
    private void enterKeyPressed() {
        System.out.println("ENTER BUTTON TESTING WORKED");
    }

    /**
     * TODO
     */
    @FXML
    private void backButtonPressed() {
        MainFXApplication.showMapSelectScreen();
    }
}
