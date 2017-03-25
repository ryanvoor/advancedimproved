package model;

// javafx imports
import javafx.scene.image.Image;

/**
 * TODO
 */
public abstract class TileOccupant {

    // instance variables
    private String fileImageUrl;

    /**
     * TODO
     */
    public TileOccupant(String fileImageUrl) {
        this.fileImageUrl = fileImageUrl;
    }

    /////////////
    // Getters //
    /////////////

    /**
     * TODO
     */
    public String getFileImageUrl() {
        return this.fileImageUrl;
    }

    /**
     * TODO
     */
    public Image getImage() {
        return new Image(this.getFileImageUrl());
    }
}
