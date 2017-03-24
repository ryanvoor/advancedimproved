package controller;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;

import fxapp.MainFXApplication;
import model.Map;

/**
 * Controller for Gameplay Screen
 */
public class GameplayScreenController extends Controller {

    // regular instance variables
    private Map map;

    // FXML instance variables
    // these get set automatically by javafx and the fxml
    @FXML
    private Canvas mapCanvas;

    @Override
    public void initialize() {
        // make sure that the map has been set
        // basically we need the setMap method to have
        // been called before we can run this method successfully
        if (null == map) {
            // TODO throw an exception
        }

        // TODO show the map on the mapCanvas
        // will probably end up passing the mapCanvas to a method in Map
        // because it makes sense for the map to know how to draw itself
    }

    /**
     * setter for the map instance variable
     * @param map the map to set to this controller's instance variable
     * @return whether the map was successfully set or not
     */
    public boolean setMap(Map map) {
        // only case where we wouldn't
        // set the map
        if (null == map) {
            return false;
        }

        // set the map and tell caller of
        // this method we set the map
        this.map = map;
        return true;
    }

    // TODO this might not be working since I can't focus the canvas since there's nothing inside it
    /**
     * executes when enter key is pressed
     */
    @FXML
    private void enterKeyPressed() {
        System.out.println("ENTER BUTTON TESTING WORKED");
    }

    /**
     * executes when the back button on the Gameplay Screen
     * is pressed, it shows the Map Select Screen
     */
    @FXML
    private void backButtonPressed() {
        MainFXApplication.showMapSelectScreen();
    }
}
