package model.drawable.tileOccupant;

// java standard library imports
import java.util.ArrayList;

/**
 * abstract class that represents a unit on the Map that
 * moves using its feet
 * @author Ryan Voor
 */
public abstract class FootMovementUnit extends TileOccupant {

    // TODO implement ability for all subclasses of this to know how far
    // they can move (total not counting movement costs since that's
    // handled in the Terrain classes)

    /**
     * constructor for the FootMovementUnit class
     * @param fileImageUrls the urls of the files of
     * the Images that should be shown on the Tile
     * that this FootMovementUnit occupies
     */
    public FootMovementUnit(ArrayList<String> fileImageUrls) {
        super(fileImageUrls);
    }
}
