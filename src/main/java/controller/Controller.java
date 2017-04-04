package controller;

/**
 * Abstract class that represents a Controller
 * of a view in the application
 * @author Ryan Voor
 */
public abstract class Controller {

    /**
     * method that gets called to intialize and
     * set up things in the controller before the
     * view is displayed (gets called in MainFXApplication
     * after the default initialize() method from
     * the javafx classes)
     */
    public void setupController() {
        // do nothing by default
        // this might change as my controllers become more complex
        return;
    }

}
