package model;

// java standard library imports
import java.io.FileNotFoundException;

// javafx imports
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;

// this project imports
import model.map.Map;
import exception.MapFileReadException;

/**
 * Represents the access point into the Model for
 * the Controllers, This class should contain no
 * actual logic but it should connect abstracted function
 * calls that the Controllers can see to actual Model
 * methods
 * @author Ryan Voor
 */
public class Facade {

    /**
     * draws the parameter Map onto the parameter Canvas
     * @param Map the map that will get drawn onto the canvas
     * @param Canvas the canvas upon which the map will get drawn
     */
    public static void drawMap(Map map, Canvas mapCanvas) {
        map.draw(mapCanvas);
    }

    /**
     * builds a Map object given the path to a file that holds the
     * information for the Tiles of that Map
     * @throws FileNotFoundException gets thrown if a Map file cannot be
     * found at the parameter file path
     * @throws MapFileReadException gets thrown if the Map file is invalid
     * and cannot be read
     * @param mapFilePath the file path to the file that represents
     * the map that should be built
     */
    public static Map buildMap(String mapFilePath)
        throws FileNotFoundException, MapFileReadException {
        return Map.buildMap(mapFilePath);
    }

    /**
     * given an X-coordinate in pixels returns
     * the tile column on a Map that would contain those coordinates
     * @param xCoordinate the X coordinate that is contained by the
     * Tile that will be returned
     * @return int the column that contains the parameter coordinate
     */
    public static int getColumnFromCoordinate(int xCoordinate) {
        return Map.getColumnFromCoordinate(xCoordinate);
    }

    /**
     * given a Y-coordinate in pixels returns
     * the tile row on this Map that contains those coordinates
     * @param yCoordinate the Y coordinate that is contained by the
     * Tile that will be returned
     * @return int the row that contains the parameter coordinate
     */
    public static int getRowFromCoordinate(int yCoordinate) {
        return Map.getRowFromCoordinate(yCoordinate);
    }

    /**
     * tint the tile at the parameter indices to the parameter
     * color and alpha
     * @param mapCanvas the canvas upon which this Map is drawn
     * @param columnIndex the X-Index of the Tile that we are tinting
     * @param rowIndex the Y-Index of the Tile that we are tinting
     * @param color the Color to tint the Tile
     * @param alpha the Alpha value used to determine transparency,
     * between 0.0 and 1.0 and lower is more transparent
     */
    public static void tintTile(Canvas mapCanvas,
        int columnIndex, int rowIndex, Color color, double alpha) {
        Map.tintTile(mapCanvas, columnIndex, rowIndex, color, alpha);
    }
}
