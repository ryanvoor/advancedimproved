package model;

// javafx imports
import javafx.scene.image.Image;

/**
 * TODO
 */
public class Forest extends Terrain {
    //private final int ???MovementCost = ???;

    /**
     * TODO
     */
    public Forest() {
        // TODO anything to do here?
    }

    @Override
    public int getMovementCost(TileOccupant unit) {
        // TODO
        //if (unit instanceof ???) {
        //    return ???;
        //}
        return 0;
    }

    @Override
    public Image getImage() {
        // TODO this is temporary for compilation purposes
        return null;
    }
}
