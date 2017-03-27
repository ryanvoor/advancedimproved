package model;

// java standard library imports
import java.io.FileNotFoundException;

// javafx imports
import javafx.scene.canvas.Canvas;

// this project imports
import model.map.Map;
import exception.MapFileReadException;

/**
 * TODO
 */
public class Facade {

    /**
     * TODO
     */
    public static void drawMap(Map map, Canvas mapCanvas) {
        map.draw(mapCanvas);
    }

    /**
     * TODO
     */
    public static Map buildMap(String mapFilePath) throws FileNotFoundException, MapFileReadException {
        return Map.buildMap(mapFilePath);
    }

}
