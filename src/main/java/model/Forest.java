package model;

// javafx imports
import javafx.scene.image.Image;

/**
 * TODO
 */
public class Forest extends Terrain {

    /**
     * TODO
     */
    public Forest() {
        super("File:lib/images/forest.png");
    }

    @Override
    public int getMovementCost(TileOccupant unit) {
        // TODO
        //if (unit instanceof ???) {
        //    return ???;
        //}
        return 0;
    }
}
