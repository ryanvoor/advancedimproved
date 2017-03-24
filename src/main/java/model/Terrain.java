package model;

// javafx imports
import javafx.scene.image.Image;

/**
 * TODO
 */
public abstract class Terrain {

    /**
     * TODO
     */
    public abstract int getMovementCost(TileOccupant unit);

    /**
     * TODO
     */
    public abstract Image getImage();

}
