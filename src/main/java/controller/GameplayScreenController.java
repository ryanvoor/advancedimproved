package controller;

// javafx imports
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;

// this project imports
import fxapp.MainFXApplication;
import model.Facade;
import model.map.Map;

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
    public void setupController() {
        // make sure that the map has been set
        // basically we need the setMap method to have
        // been called before we can run this method successfully
        if (null == map) {
            System.out.println(
                "Map cannot be null when "
                + "GameplayScreenController is being setup"
            );
            System.exit(0);
        }

        // draw the Map onto the screen
        Facade.drawMap(this.getMap(), this.getMapCanvas());

        // TODO for these three related tasks see this link for help:
/*
http://stackoverflow.com/ (cont on next line for checkstyle purposes)
questions/29962395/how-to-write-a-keylistener-for-javafx
*/

        // set up key event handler
        // TODO

        // set up mouse event handler
        // TODO

        // set up animation timer (redraws canvas every frame)
        // TODO
    }

    /////////////
    // Getters //
    /////////////

    /**
     * getter for the Map object that gets drawn on
     * the screen that this Controller controls
     * @return Map the map that is being displayed
     * on the screen that this Controller controls
     */
    public Map getMap() {
        return this.map;
    }

    /**
     * getter for the Canvas object upon
     * which the Map is drawn
     * @return Canvas the canvas upon which
     * the map is drawn
     */
    public Canvas getMapCanvas() {
        return this.mapCanvas;
    }

    /////////////
    // Setters //
    /////////////

    /**
     * setter for the map instance variable
     * @param map the map to set to this controller's instance variable
     * @return whether the map was successfully set or not
     */
    public boolean setMap(Map map) {
        if (null == map) {
            // this is the only case where we wouldn't
            // set the map successfully
            return false;
        }

        // set the map and tell caller of
        // this method we set the map
        this.map = map;
        return true;
    }

    // TODO this might not be working since I can't
    // focus the canvas since there's nothing inside it
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
