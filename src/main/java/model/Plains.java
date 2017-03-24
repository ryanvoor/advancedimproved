package model;

// javafx imports
import javafx.scene.image.Image;

/**
 * TODO
 */
public class Plains extends Terrain {

    /**
     * TODO
     */
    public Plains() {
        super("file:lib/images/Plains.jpg");
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
