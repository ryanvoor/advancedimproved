package controller;

// javafx imports
import javafx.scene.Scene;

/**
 * Abstract class that represents a Controller
 * of a view in the application
 * @author Ryan Voor
 */
public abstract class Controller {

    // instance variables
    private Scene scene;

    /////////////
    // Getters //
    ////////////

    /**
     * getter for the Scene of this Controller
     * @return Scene the scene of this Controller
     */
    public Scene getScene() {
        return this.scene;
    }


    /////////////
    // Setters //
    /////////////

    /**
     * setter for the Scene of this Controller
     * @param scene the scene to be set
     */
    public void setScene(Scene scene) {
        this.scene = scene;
    }


    //////////////////
    // Real Methods //
    //////////////////

    /**
     * method that gets called to intialize and
     * set up things in the controller before the
     * view is displayed (gets called in MainFXApplication
     * after the default initialize() method from
     * the javafx classes)
     */
    public void setupController() {
        // for now do nothing
        // this may change as the controllers grow in complexity
        return;
    }



}
