package controller;

// javafx imports
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.Scene;

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
    private int selectedColumn;
    private int selectedRow;
    private boolean aTileIsSelected;

    // FXML instance variables
    // these get set automatically by javafx and the fxml
    @FXML
    private Canvas mapCanvas;

    @Override
    public void setupController() {
        super.setupController();

        // make sure that the map has been set
        // basically we need the setMap method to have
        // been called before we can run this method successfully
        if (null == this.getMap()) {
            System.out.println(
                "Map cannot be null when "
                + "GameplayScreenController is being setup"
            );
            System.exit(0);
        }

        // grab the relevant instance variables
        Canvas mapCanvas = this.getMapCanvas();
        Map map          = this.getMap();
        Scene scene      = this.getScene();

        // draw the Map onto the screen
        // this call draws the Map before the AnimationTimer gets
        // set up so this is the only time that we should be passing
        // in time = 0
        Facade.drawMap(map, mapCanvas, 0);

        // TODO move these event handlers and the animation timer into
        // their own files (make them subclasses (since that's what they
        // are already implicitly)) to help keep this controller from
        // becoming gigantic

        // set up mouse event handler
        mapCanvas.addEventHandler(MouseEvent.MOUSE_CLICKED,
            new EventHandler<MouseEvent>() {
                // execute this method when user clicks on the canvas
                @Override
                public void handle(MouseEvent e) {
                    // unselect the tile that was previously selected
                    GameplayScreenController.this.unselectCurrentTile();

                    // select the tile that gets clicked on
                    GameplayScreenController.this.selectTile(
                        // cast this because I don't care about
                        // partial pixel values
                        Facade.getColumnFromCoordinate((int) e.getX()),
                        Facade.getRowFromCoordinate((int) e.getY())
                    );
                }
            });


        // set up key event handlers
        // key pressed handler
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                // check all the keys that could get pressed that I care about
                switch (event.getCode()) {
                    case ENTER:
                        GameplayScreenController.this.enterKeyPressed();
                        break;
                    default:
                        // ignore other button presses
                    break;
                }
            }
        });

        // key released handler
        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                // check all the keys that could get pressed that I care about
                switch (event.getCode()) {
                    case ENTER:
                        GameplayScreenController.this.enterKeyReleased();
                    break;
                    default:
                        // ignore other button presses
                    break;
                }
            }
        });


        // set up animation timer (redraws canvas every frame)
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                // grab variables that we'll need
                Map map          = GameplayScreenController.this.getMap();
                Canvas mapCanvas = GameplayScreenController.this.getMapCanvas();
                GraphicsContext graphicsContext
                    = mapCanvas.getGraphicsContext2D();

                // clear the map
                graphicsContext.clearRect(
                    0,
                    0,
                    mapCanvas.getHeight(),
                    mapCanvas.getWidth()
                );

                // redraw the map
                Facade.drawMap(map, mapCanvas, now);

                // highlight the selected Tile
                if (GameplayScreenController.this.getATileIsSelected()) {
                    Facade.tintTile(
                        mapCanvas,
                        GameplayScreenController.this.getSelectedColumn(),
                        GameplayScreenController.this.getSelectedRow(),
                        Color.BLUE,
                        0.3
                    );
                }
            }
        };

        // begin animation/redrawing
        timer.start();
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

    /**
     * getter for the column on the map that the
     * user currently has selected
     * @return int the column on the map that the
     * user currently has selected
     */
    private int getSelectedColumn() {
        if (!this.getATileIsSelected()) {
            // TODO change these to an exception that makes more sense
            // my custom exception class should probably be a subclass
            // of runtime exception
            throw new RuntimeException("tried to get selected column "
                + "when a tile was not selected");
        }
        return this.selectedColumn;
    }

    /**
     * getter for the row on the map that the
     * user currently has selected
     * @return int the row on the map that the
     * user currently has selected
     */
    private int getSelectedRow() {
        if (!this.getATileIsSelected()) {
            // TODO change these to an exception that makes more sense
            // my custom exception class should probably be a subclass
            // of runtime exception
            throw new RuntimeException("tried to get selected row "
                + "when a tile was not selected");
        }
        return this.selectedRow;
    }

    /**
     * returns whether the user currently has a Tile
     * on the Map selected
     * @return boolean whether the user currently has
     * a Tile on the Map selected
     */
    private boolean getATileIsSelected() {
        return this.aTileIsSelected;
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

    /**
     * setter for the column that is currently selected
     * by the user
     * @param int the column that the is to become
     * selected
     */
    public void setSelectedColumn(int selectedColumn) {
        this.selectedColumn = selectedColumn;
    }

    /**
     * setter for the row that is currently selected
     * by the user
     * @param int the row that the is to become
     * selected
     */
    public void setSelectedRow(int selectedRow) {
        this.selectedRow = selectedRow;
    }

    /**
     * setter for whether the user currently has
     * a Tile selected
     * @param boolean whether the user currently has
     * a Tile selected
     */
    public void setATileIsSelected(boolean aTileIsSelected) {
        this.aTileIsSelected = aTileIsSelected;
    }


    //////////////////
    // Real Methods //
    //////////////////

    /**
     * unselects the Tile that user has
     * selected if any
     */
    private void unselectCurrentTile() {
        // simply set the controller to not acknowlege
        // that the user has a Tile selected
        this.setATileIsSelected(false);
    }

    /**
     * selects the Tile that is located at the parameter
     * column and row
     * @param selectedColumn the column of the Tile that the
     * user is selecting
     * @param selectedRow the row of the Tile that the
     * user is selecting
     */
    private void selectTile(int selectedColumn, int selectedRow) {
        // set this controller to acknowledge that the user
        // has a Tile selected
        this.setATileIsSelected(true);

        // set which Tile the user has selected
        this.setSelectedColumn(selectedColumn);
        this.setSelectedRow(selectedRow);
    }

    //// key handler methods ////

    /**
     * executes when enter key is pressed
     */
    private void enterKeyPressed() {
        System.out.println("Enter key pressed");
    }

    /**
     * executes when enter key is released
     */
    private void enterKeyReleased() {
        System.out.println("Enter key released");
    }


    //// button handler methods ////

    /**
     * executes when the back button on the Gameplay Screen
     * is pressed, it shows the Map Select Screen
     */
    @FXML
    private void backButtonPressed() {
        MainFXApplication.showMapSelectScreen();
    }

    /**
     * executes when the clear selection button on the
     * Gameplay Screen is pressed, it unselects the
     * currently selected Tile
     */
    @FXML
    private void clearSelectionButtonPressed() {
        this.unselectCurrentTile();
    }
}
