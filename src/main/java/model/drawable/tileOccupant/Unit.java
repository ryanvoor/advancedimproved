package model.drawable.tileOccupant;

// java standard library imports
import java.util.ArrayList;

/**
 * abstract class that represents a unit on the Map
 * units are different from other TileOccupants because
 * a unit can move
 * @author Ryan Voor
 */
public abstract class Unit extends TileOccupant {

    // instance variables
    private int movementRange;

    /**
     * constructor for the Unit class
     * @param fileImageUrls the urls of the files of
     * the Images that should be shown on the Tile
     * that this Unit occupies
     */
    public Unit(ArrayList<String> fileImageUrls, int movementRange) {
        super(fileImageUrls);
        this.movementRange = movementRange;
    }

    /////////////
    // Getters //
    /////////////

    /**
     * getter for the movementRange of this Unit,
     * this movementRange is before Terrain calculations
     * are taken into account
     * @return int the movementRange of this Unit
     */
    public int getMovementRange() {
        return this.movementRange;
    }
}
