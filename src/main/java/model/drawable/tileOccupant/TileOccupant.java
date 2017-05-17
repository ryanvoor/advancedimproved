package model.drawable.tileOccupant;

// java standard library imports
import java.util.ArrayList;

// javafx imports
import javafx.scene.image.Image;

// this project imports
import model.drawable.Drawable;

/**
 * abstract class that represents something on the Map
 * that occupies a Tile, these can be units in an
 * army, cities or other buildings and anything else we
 * come up with to put onto Tiles
 * @author Ryan Voor
 */
public abstract class TileOccupant extends Drawable {

    // instance variables
    private int movementRange;

    /**
     * constructor for the TileOccupant class
     * @param imageFileUrls the urls of the files of
     * the Images that should be shown on the Tile
     * that this TileOccupant occupies
     */
    public TileOccupant(ArrayList<String> imageFileUrls, int movementRange) {
        super(imageFileUrls);
        this.movementRange = movementRange;
    }

    /////////////
    // Getters //
    /////////////

    /**
     * getter for the movementRange of this TileOccupant,
     * this movementRange is before Terrain calculations
     * are taken into account
     * @return int the movementRange of this Unit
     */
    public int getMovementRange() {
        return this.movementRange;
    }
}
