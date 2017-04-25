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

    // TODO there's a problem with the canvas being bigger than the map and
    // that causing arrayindexoutofbounds exceptions. However, it doesnt really
    // affect the user experience directly so i guess fixing it isnt super high
    // priority

    // regular instance variables
    private Map map;
    private int selectedColumn;
    private int selectedRow;
    private boolean aTileIsSelected;
    private int hoveredColumn; // the default hovered Tile should be 0,0
    private int hoveredRow;    // the default hovered Tile should be 0,0

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

        // set up mouse click event handler
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

        // set up mouse move event handler
        mapCanvas.addEventHandler(MouseEvent.MOUSE_MOVED,
            new EventHandler<MouseEvent>() {
                // execute this method when user moves the mouse on the canvas
                @Override
                public void handle(MouseEvent e) {
                    // if there is no selected Tile
                    // this is safe because the only Tile that should
                    // ever get selected is the Tile that was just being
                    // hovered
                    if (!GameplayScreenController.this.getATileIsSelected()) {
                        // hover the tile that get moved onto
                        GameplayScreenController.this.hoverTile(
                            // cast this because I don't care about
                            // partial pixel values
                            Facade.getColumnFromCoordinate((int) e.getX()),
                            Facade.getRowFromCoordinate((int) e.getY())
                        );
                    }
                }
            });


        // set up key event handlers
        // key pressed handler
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                // check all the keys that could get pressed that I care about
                switch (event.getCode()) {
                    case W:
                        GameplayScreenController.this.wKeyPressed();
                        break;
                    case A:
                        GameplayScreenController.this.aKeyPressed();
                        break;
                    case S:
                        GameplayScreenController.this.sKeyPressed();
                        break;
                    case D:
                        GameplayScreenController.this.dKeyPressed();
                        break;
                    case ENTER:
                        GameplayScreenController.this.enterKeyPressed();
                        break;
                    case ESCAPE:
                        GameplayScreenController.this.escapeKeyPressed();
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
                // make the framerate 60 FPS by not
                // executing the rest of this method if we aren't
                // to the next 1/60 of a second interval
                // 16670000L is 1/60th of a second in nanoseconds
                if (0 == (now % 16670000L)) {
                    return;
                }

                // grab variables that we'll need
                Map map          = GameplayScreenController.this.getMap();
                Canvas mapCanvas = GameplayScreenController.this.getMapCanvas();
                GraphicsContext graphicsContext
                    = mapCanvas.getGraphicsContext2D();
                boolean aTileIsSelected
                    = GameplayScreenController.this.getATileIsSelected();
                boolean selectedTileIsOccupied
                    = GameplayScreenController.this.selectedTileIsOccupied();
                int selectedColumn
                    = GameplayScreenController.this.getSelectedColumn();
                int selectedRow
                    = GameplayScreenController.this.getSelectedRow();
                int hoveredColumn
                    = GameplayScreenController.this.getHoveredColumn();
                int hoveredRow
                    = GameplayScreenController.this.getHoveredRow();

                // clear the map
                GameplayScreenController.this.clearMapCanvas();

                // redraw the map
                Facade.drawMap(map, mapCanvas, now);

                // if there is a currently selected Tile
                if (aTileIsSelected) {
                    // highlight the selected Tile
                    Facade.tintTile(
                        mapCanvas,
                        selectedColumn,
                        selectedRow,
                        Color.BLUE,
                        0.3
                    );

                    // if the selected Tile is occupied then highlight all
                    // the Tiles that the TileOccupant can move to
                    if (selectedTileIsOccupied) {
                        // if the tile that is selected is occupied
                        // then highlight where the tile occupant can move
                        Facade.tintTilesToWhichOccupantCanMove(
                            map,
                            mapCanvas,
                            selectedColumn,
                            selectedRow,
                            Color.RED,
                            0.3
                        );
                    }

                }

                // if there is a currently selected Tile and if
                // the currently Selected Tile is the same one that
                // is being hovered then don't draw the hovered Tile,
                // if there is no currently selected Tile then draw
                // the hovered Tile
                if (aTileIsSelected && (selectedColumn != hoveredColumn
                    || selectedRow != hoveredRow) || !aTileIsSelected) {
                    Facade.tintTile(
                        mapCanvas,
                        hoveredColumn,
                        hoveredRow,
                        Color.GREEN,
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
     * NOTE: never use this without first checking
     * the boolean that says whether this column is valid
     * data that can be used or not
     * @return int the column on the map that the
     * user currently has selected
     */
    private int getSelectedColumn() {
        return this.selectedColumn;
    }

    /**
     * getter for the row on the map that the
     * user currently has selected
     * NOTE: never use this without first checking
     * the boolean that says whether this row is valid
     * data that can be used or not
     * @return int the row on the map that the
     * user currently has selected
     */
    private int getSelectedRow() {
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

    /**
     * getter for the column on the map that the
     * user currently has hovered, this is slightly
     * different from the selected Tile because
     * there is always a hovered Tile
     * @return int the column on the map that the
     * user currently has hovered
     */
    private int getHoveredColumn() {
        return this.hoveredColumn;
    }

    /**
     * getter for the row on the map that the
     * user currently has hovered, this is slightly
     * different from the selected Tile because
     * there is always a hovered Tile
     * @return int the row on the map that the
     * user currently has hovered
     */
    private int getHoveredRow() {
        return this.hoveredRow;
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

    /**
     * setter for the column that is currently hovered
     * by the user
     * @param int the column that the is to become
     * hovered
     */
    public void setHoveredColumn(int hoveredColumn) {
        this.hoveredColumn = hoveredColumn;
    }

    /**
     * setter for the row that is currently hovered
     * by the user
     * @param int the row that the is to become
     * hovered
     */
    public void setHoveredRow(int hoveredRow) {
        this.hoveredRow = hoveredRow;
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

    /**
     * returns whether the currently selected Tile is occupied
     * or not, expects that there IS a currently selected Tile
     * when this method is called
     * @return boolean whether the currently selected Tile is
     * occupied or not
     */
    private boolean selectedTileIsOccupied() {
        return Facade.tileIsOccupied(
            this.getMap(),
            this.getSelectedColumn(),
            this.getSelectedRow()
        );
    }

    /**
     * hovers the Tile that is located at the parameter
     * column and row
     * @param hoveredColumn the column of the Tile that the
     * user is hovering
     * @param hoveredRow the row of the Tile that the
     * user is hovering
     */
    private void hoverTile(int hoveredColumn, int hoveredRow) {
        // set which Tile the user has selected
        this.setHoveredColumn(hoveredColumn);
        this.setHoveredRow(hoveredRow);
    }

    /**
     * completely clears the Canvas on this Screen with
     * the Map drawn on it
     */
    private void clearMapCanvas() {
        // grab the mapCanvas
        Canvas mapCanvas                = this.getMapCanvas();
        GraphicsContext graphicsContext = mapCanvas.getGraphicsContext2D();

        // clear the canvas
        graphicsContext.clearRect(
            0,
            0,
            mapCanvas.getHeight(),
            mapCanvas.getWidth()
        );
    }

    /**
     * moves the currently hovered Tile to the north by
     * one Tile on the condition that we are not at the
     * north most row on the Map
     */
    private void moveHoverNorthByOne() {
        // grab the currently hovered row
        int hoveredRow = this.getHoveredRow();

        // check if we are at the north-most row on the
        // map, if so then we don't move the hover north
        if (0 < hoveredRow) {
            this.setHoveredRow(hoveredRow - 1);
        }
    }

    /**
     * moves the currently hovered Tile to the east by
     * one Tile on the condition that we are not at the
     * east most column on the Map
     */
    private void moveHoverEastByOne() {
        // grab the currently hovered column
        int hoveredColumn = this.getHoveredColumn();

        // check if we are at the east-most column on the
        // map, if so then we don't move the hovered Tile east
        if (Facade.getMaxXIndex(this.getMap()) > hoveredColumn) {
            this.setHoveredColumn(hoveredColumn + 1);
        }
    }

    /**
     * moves the currently hovered Tile to the south by
     * one Tile on the condition that we are not at the
     * south most row on the Map
     */
    private void moveHoverSouthByOne() {
        // grab the currently hovered row
        int hoveredRow = this.getHoveredRow();

        // check if we are at the south-most column on the
        // map, if so then we don't move the hovered Tile south
        if (Facade.getMaxYIndex(this.getMap()) > hoveredRow) {
            this.setHoveredRow(hoveredRow + 1);
        }
    }

    /**
     * moves the currently hovered Tile to the west by
     * one Tile on the condition that we are not at the
     * west most column on the Map
     */
    private void moveHoverWestByOne() {
        // grab the currently hovered column
        int hoveredColumn = this.getHoveredColumn();

        // check if we are at the west-most column on the
        // map, if so then we don't move the hovered Tile west
        if (0 < hoveredColumn) {
            this.setHoveredColumn(hoveredColumn - 1);
        }
    }


    //// key handler methods ////

    /**
     * executes when the 'w' key is pressed,
     * it moves the currently hovered Tile to the
     * north by one Tile if we aren't at the edge of
     * the Map
     */
    private void wKeyPressed() {
        this.moveHoverNorthByOne();
    }

    /**
     * executes when the 'a' key is pressed,
     * it moves the currently hovered Tile to the
     * west by one Tile if we aren't at the edge of
     * the Map
     */
    private void aKeyPressed() {
        this.moveHoverWestByOne();
    }

    /**
     * executes when the 's' key is pressed,
     * it moves the currently hovered Tile to the
     * south by one Tile if we aren't at the edge of
     * the Map
     */
    private void sKeyPressed() {
        this.moveHoverSouthByOne();
    }

    /**
     * executes when the 'd' key is pressed,
     * it moves the currently hovered Tile to the
     * east by one Tile if we aren't at the edge of
     * the Map
     */
    private void dKeyPressed() {
        this.moveHoverEastByOne();
    }

    /**
     * executes when enter key is pressed,
     * selects the currently hovered Tile
     */
    private void enterKeyPressed() {
        this.selectTile(
            this.getHoveredColumn(),
            this.getHoveredRow()
        );
    }

    /**
     * executes when the esc key is pressed,
     * unselects the currently selected Tile
     */
    private void escapeKeyPressed() {
        this.unselectCurrentTile();
    }

    /**
     * executes when enter key is released,
     * for now it just prints something out
     * for testing purposes
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
