package model;

// java standard library imports
import java.io.FileNotFoundException;

// javafx imports
import javafx.scene.canvas.Canvas;

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
    public static Map buildMap(String mapFilePath) throws FileNotFoundException, MapFileReadException {
        return Map.buildMap(mapFilePath);
    }

}
