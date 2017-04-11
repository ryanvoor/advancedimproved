package model.drawable.terrain;

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
public abstract class Terrain implements Drawable {

    // constants
    private String imageFileUrl;

    /**
     * constructor for the Terrain class
     * @param imageFileUrl the url of the file
     * that will be displayed for this Terrain
     */
    public Terrain(String imageFileUrl) {
        this.imageFileUrl = imageFileUrl;
    }

    //////////////////////
    // Abstract Methods //
    //////////////////////

    /**
     * returns the movement cost that a unit needs to pay
     * to move onto a Tile that has this Terrain
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
        return this.imageFileUrl;
    }

    @Override
    public Image getImage(long time) {
        // just ignore the time parameter since this
        // class isn't animated
        return new Image(this.getImageFileUrl());
    }
}
