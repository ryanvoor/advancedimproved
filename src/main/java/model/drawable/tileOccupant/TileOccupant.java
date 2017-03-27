package model.drawable.tileOccupant;

// javafx imports
import javafx.scene.image.Image;

// this project imports
import model.drawable.Drawable;

/**
 * TODO
 */
public abstract class TileOccupant implements Drawable {

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
