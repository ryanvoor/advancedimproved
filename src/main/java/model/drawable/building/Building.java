package model.drawable.building;

// java standard library imports
import java.util.ArrayList;

// javafx imports
import javafx.scene.image.Image;

// this project imports
import model.drawable.Drawable;

/**
 * abstract class that represents a building on the Map
 * that is located on a Tile, these can produce units or
 * heal units or provide vision etc.
 * @author Ryan Voor
 */
public abstract class Building extends Drawable {


    /**
     * constructor for the Building class
     * @param imageFileUrls the urls of the files of
     * the Images that should be shown on the Tile
     * that this Buildling is located on
     */
    public Building(ArrayList<String> imageFileUrls) {
        super(imageFileUrls);
    }
}
