package model.drawable.tileOccupant;

// java standard library imports
import java.util.ArrayList;

/**
 * abstract class that represents a unit on the Map that
 * moves using its feet
 * @author Ryan Voor
 */
public abstract class FootMovementUnit extends Unit {

    /**
     * constructor for the FootMovementUnit class
     * @param fileImageUrls the urls of the files of
     * the Images that should be shown on the Tile
     * that this FootMovementUnit occupies
     */
    public FootMovementUnit(ArrayList<String> fileImageUrls,
        int movementRange) {
        super(fileImageUrls, movementRange);
    }
}
