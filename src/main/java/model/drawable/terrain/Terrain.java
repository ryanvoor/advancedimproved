package model.drawable.terrain;

// java standard library imports
import java.util.ArrayList;
import java.util.Arrays;

// javafx imports
import javafx.scene.image.Image;

// this project imports
import model.drawable.Drawable;
import model.drawable.tileOccupant.TileOccupant;

/**
 * represents the Terrain that units stand upon and move across
 * on a particular Tile on the Map
 * @author Ryan Voor
 */
public abstract class Terrain extends Drawable {

    /**
     * constructor for the Terrain class
     * @param imageFileUrl the url of the file
     * that will be displayed for this Terrain
     */
    public Terrain(String imageFileUrl) {
        super(new ArrayList<String>(Arrays.asList(imageFileUrl)));
    }

    //////////////////////
    // Abstract Methods //
    //////////////////////

    /**
     * returns the movement cost that a unit needs to pay
     * to move onto a Tile that has this Terrain,
     * returns Integer.MAX_VALUE if the Unit cannot move
     * onto a Tile with this Terrain,
     * NOTE: the implementations of this methods will need
     * to be updated every time a new unit-type is added
     * @param unit the TileOccupant that is attempting
     * to move onto the Tile that has this Terrain
     * @return int the movement cost that the parameter
     * unit must pay in order to move onto a Tile that
     * has this Terrain
     */
    public abstract int getMovementCost(TileOccupant unit);

    /////////////
    // Getters //
    /////////////

    /**
     * getter for the url of the image that
     * should be displayed for this Terrain
     * @return String the url of the image that
     * should be displayed for this Terrain
     */
    public String getImageFileUrl() {
        return this.getImageFileUrls().get(0);
    }
}
