package model.drawable.tileOccupant;

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
public abstract class TileOccupant implements Drawable {

    // instance variables
    private String fileImageUrl;

    /**
     * constructor for the TileOccupant class
     * @param fileImageUrl the url of the file of
     * the Image that should be shown on the Tile
     * that this TileOccupant occupies
     */
    public TileOccupant(String fileImageUrl) {
        this.fileImageUrl = fileImageUrl;
    }

    /////////////
    // Getters //
    /////////////

    /**
     * getter for the url of the File that
     * @return String the url of the file of
     * the Image that should be shown on the
     * Tile that this TileOccupant occupies
     */
    public String getFileImageUrl() {
        return this.fileImageUrl;
    }

    /**
     * getter for the Image that should be shown
     * on the Tile that this TileOccupant occupies
     * @return Image the Image that should be shown
     * on the Tile that this TileOccupant occupies
     */
    public Image getImage() {
        return new Image(this.getFileImageUrl());
    }
}
