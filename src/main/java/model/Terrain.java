package model;

// javafx imports
import javafx.scene.image.Image;

/**
 * TODO
 */
public abstract class Terrain implements Drawable {

    // constants
    private String imageFileUrl;

    /**
     * TODO
     */
    public Terrain(String imageFileUrl) {
        this.imageFileUrl = imageFileUrl;
    }

    //////////////////////
    // Abstract Methods //
    //////////////////////

    /**
     * TODO
     */
    public abstract int getMovementCost(TileOccupant unit);

    /////////////
    // Getters //
    /////////////

    /**
     * TODO
     */
    public String getImageFileUrl() {
        return this.imageFileUrl;
    }

	/**
 	 * TODO
	 */
    public Image getImage() {
        return new Image(this.getImageFileUrl());
    }
}
