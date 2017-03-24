package model;

// javafx imports
import javafx.scene.image.Image;

/**
 * TODO
 */
public class Mountain extends Terrain {

    /**
     * TODO
     */
    public Mountain() {
        super("file:lib/images/mountain.jpg");
    }

    /**
     * TODO
     */
    public int getMovementCost(TileOccupant unit) {
        // TODO
        //if (unit instanceof ???) {
        //    return ???;
        //}
        return 0;
    }
}
